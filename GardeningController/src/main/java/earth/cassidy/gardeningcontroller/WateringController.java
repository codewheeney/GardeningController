package earth.cassidy.gardeningcontroller;

import com.google.common.annotations.VisibleForTesting;
import earth.cassidy.gardeningcontroller.schedule.DailySchedule;
import earth.cassidy.gardeningcontroller.schedule.TimedAction;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WateringController implements Runnable {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().toString());
  private final DailySchedule dailySchedule;
  private final BiConsumer<Duration, String> turnOnMethod;
  private final BiConsumer<Duration, String> turnOffMethod;

  public WateringController(final DailySchedule dailySchedule,
      final BiConsumer<Duration, String> turnOnMethod, final BiConsumer<Duration, String> turnOffMethod) {
    this.dailySchedule = dailySchedule;
    this.turnOnMethod = turnOnMethod;
    this.turnOffMethod = turnOffMethod;
  }

  @Override
  public void run() {
    try {
      log.log(Level.FINE, "Checking schedule");

      final LocalTime now = LocalTime.now();
      final LocalDate day = LocalDate.now();

      switch(day.getDayOfWeek()) {
        case MONDAY -> executeDay(now, dailySchedule.mondayTimes(), turnOnMethod, turnOffMethod);
        case TUESDAY -> executeDay(now, dailySchedule.tuesdayTimes(), turnOnMethod, turnOffMethod);
        case WEDNESDAY -> executeDay(now, dailySchedule.wednesdayTimes(), turnOnMethod, turnOffMethod);
        case THURSDAY -> executeDay(now, dailySchedule.thursdayTimes(), turnOnMethod, turnOffMethod);
        case FRIDAY -> executeDay(now, dailySchedule.fridayTimes(), turnOnMethod, turnOffMethod);
        case SATURDAY -> executeDay(now, dailySchedule.saturdayTimes(), turnOnMethod, turnOffMethod);
        case SUNDAY -> executeDay(now, dailySchedule.sundayTimes(), turnOnMethod, turnOffMethod);
      }

      log.log(Level.FINE, "Done schedule");
    } catch (final Exception ex) {
      log.log(Level.SEVERE, String.format("Exception executing schedule %s, %s", dailySchedule, ex));
    }
  }

  @VisibleForTesting
  protected static void executeDay(final LocalTime now, final List<TimedAction> actionList,
      final BiConsumer<Duration, String> turnOnMethod, final BiConsumer<Duration, String> turnOffMethod) {
    for(final TimedAction ta : actionList) {
      if(now.isAfter(ta.startTime()) && now.isBefore(ta.startTime().plus(ta.duration()))){
        log.log(Level.FINE, String.format("%s matches %s, turning on", now, ta));
        
        turnOnMethod.accept(ta.duration(), ta.startAction());
      } else {
        log.log(Level.FINE, String.format("%s does not match %s, turning off", now, ta));

        turnOffMethod.accept(ta.duration(), ta.endAction());
      }
    }
  }
}
