package earth.cassidy.gardeningcontroller;

import com.google.common.annotations.VisibleForTesting;
import earth.cassidy.gardeningcontroller.schedule.DailySchedule;
import earth.cassidy.gardeningcontroller.schedule.WateringTime;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WateringController implements Runnable {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().toString());
  private final DailySchedule dailySchedule;
  private final Consumer<Duration> turnOnMethod;

  public WateringController(final DailySchedule dailySchedule, final Consumer<Duration> turnOnMethod) {
    this.dailySchedule = dailySchedule;
    this.turnOnMethod = turnOnMethod;
  }

  @Override
  public void run() {
    try {
      log.log(Level.FINE, "Checking schedule");

      final LocalTime now = LocalTime.now();
      final LocalDate day = LocalDate.now();

      switch(day.getDayOfWeek()) {
        case MONDAY -> executeDay(now, dailySchedule.mondayTimes(), turnOnMethod);
        case TUESDAY -> executeDay(now, dailySchedule.tuesdayTimes(), turnOnMethod);
        case WEDNESDAY -> executeDay(now, dailySchedule.wednesdayTimes(), turnOnMethod);
        case THURSDAY -> executeDay(now, dailySchedule.thursdayTimes(), turnOnMethod);
        case FRIDAY -> executeDay(now, dailySchedule.fridayTimes(), turnOnMethod);
        case SATURDAY -> executeDay(now, dailySchedule.saturdayTimes(), turnOnMethod);
        case SUNDAY -> executeDay(now, dailySchedule.sundayTimes(), turnOnMethod);
      }

      log.log(Level.FINE, "Done schedule");
    } catch (final Exception ex) {
      log.log(Level.SEVERE, String.format("Exception executing schedule %s, %s", dailySchedule, ex));
    }
  }

  @VisibleForTesting
  protected static void executeDay(final LocalTime now, final List<WateringTime> times, final Consumer<Duration> turnOnMethod) {
    for(final WateringTime wt : times) {
      if(now.isAfter(wt.startTime()) && now.isBefore(wt.startTime().plus(wt.duration()))){
        log.log(Level.FINE, String.format("%s matches %s", now, wt));
        
        turnOnMethod.accept(wt.duration());
      }
    }
  }
}
