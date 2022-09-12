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
  private final ITimedActionConsumer timedActionConsumer;

  public WateringController(final DailySchedule dailySchedule, ITimedActionConsumer timedActionConsumer) {
    this.dailySchedule = dailySchedule;
    this.timedActionConsumer = timedActionConsumer;
  }

  @Override
  public void run() {
    try {
      log.log(Level.FINE, "Checking schedule");

      final LocalTime now = LocalTime.now();
      final LocalDate day = LocalDate.now();

      switch(day.getDayOfWeek()) {
        case MONDAY -> executeDay(now, dailySchedule.mondayTimes(), timedActionConsumer);
        case TUESDAY -> executeDay(now, dailySchedule.tuesdayTimes(), timedActionConsumer);
        case WEDNESDAY -> executeDay(now, dailySchedule.wednesdayTimes(), timedActionConsumer);
        case THURSDAY -> executeDay(now, dailySchedule.thursdayTimes(), timedActionConsumer);
        case FRIDAY -> executeDay(now, dailySchedule.fridayTimes(), timedActionConsumer);
        case SATURDAY -> executeDay(now, dailySchedule.saturdayTimes(), timedActionConsumer);
        case SUNDAY -> executeDay(now, dailySchedule.sundayTimes(), timedActionConsumer);
      }

      log.log(Level.FINE, "Done schedule");
    } catch (final Exception ex) {
      log.log(Level.SEVERE, String.format("Exception executing schedule %s, %s", dailySchedule, ex));
    }
  }

  @VisibleForTesting
  protected static void executeDay(final LocalTime now, final List<TimedAction> actionList,
      final ITimedActionConsumer timedActionConsumer) {
    for(final TimedAction ta : actionList) {
      if(now.isAfter(ta.startTime()) && now.isBefore(ta.startTime().plus(ta.duration()))){
        log.log(Level.FINE, String.format("%s matches %s, turning on", now, ta));
        
        timedActionConsumer.onTurnOn(ta);
      } else {
        log.log(Level.FINE, String.format("%s does not match %s, turning off", now, ta));

        timedActionConsumer.onTurnOff(ta);
      }
    }
  }
}
