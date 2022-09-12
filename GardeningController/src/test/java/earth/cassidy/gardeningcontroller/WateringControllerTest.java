package earth.cassidy.gardeningcontroller;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.util.concurrent.Uninterruptibles;
import earth.cassidy.gardeningcontroller.schedule.DailySchedule;
import earth.cassidy.gardeningcontroller.schedule.TimedAction;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import org.assertj.core.api.Fail;
import org.testng.annotations.Test;

@Test
public class WateringControllerTest {
  @Test
  public void testExecuteDayMatches() {
    final LocalTime now = LocalTime.now();
    final Duration onDuration = Duration.ofMinutes(15);
    final Duration offDuration = Duration.ofMinutes(16);
    final String startAction = "startAction";
    final String endAction = "endAction";

    final List<TimedAction> timedActions =
        List.of(
            TimedAction.builder()
                .startTime(now.plus(Duration.ofHours(10)))
                .duration(offDuration)
                .startAction("foo")
                .endAction("foo")
                .build(),
            TimedAction.builder()
                .startTime(now)
                .duration(onDuration)
                .startAction(startAction)
                .endAction(endAction)
                .build(),
            TimedAction.builder()
                .startTime(now.minus(Duration.ofHours(10)))
                .duration(onDuration.minusMinutes(1))
                .startAction("foo")
                .endAction("foo")
                .build());

    final DailySchedule dailySchedule =
        DailySchedule.builder()
            .mondayTimes(timedActions)
            .tuesdayTimes(timedActions)
            .wednesdayTimes(timedActions)
            .thursdayTimes(timedActions)
            .fridayTimes(timedActions)
            .saturdayTimes(timedActions)
            .sundayTimes(timedActions)
            .build();

    ITimedActionConsumer timedActionConsumer = new ITimedActionConsumer() {
        @Override
        public void onTurnOn(TimedAction timedAction) {
            assertThat(timedAction.duration()).isEqualTo(onDuration);
            assertThat(timedAction.startAction()).isEqualTo(startAction);

        }

        @Override
        public void onTurnOff(TimedAction timedAction) {
            assertThat(timedAction.duration()).isNotEqualTo(onDuration);
            assertThat(timedAction.endAction()).isNotEqualTo(endAction);
        }
    };

    final WateringController wateringController =
        new WateringController(dailySchedule, timedActionConsumer);

    wateringController.run();

    // TODO: Use Mockito to verify function calls
    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
  }

  /*
  @Test
  public void testExecuteNoDayMatches() {
    final LocalTime now = LocalTime.now();
    final LocalDate date = LocalDate.now();
    final Duration duration = Duration.ofMinutes(15);

    final List<TimedAction> timedActions =
        List.of(
            TimedAction.builder()
                .startTime(now.plus(Duration.ofHours(10)))
                .duration(duration.plusMinutes(1))
                .startAction("foo")
                .endAction("foo")
                .build(),
            TimedAction.builder()
                .startTime(now.plus(duration.plusMinutes(1)))
                .duration(duration)
                .startAction("foo")
                .endAction("foo")
                .build(),
            TimedAction.builder()
                .startTime(now.minus(Duration.ofHours(10)))
                .duration(duration.minusMinutes(1))
                .startAction("foo")
                .endAction("foo")
                .build());

    final BiConsumer<Duration, String> turnOnMethod =
        new BiConsumer<>() {
          @Override
          public void accept(final Duration duration, final String payload) {
            Fail.fail(
                String.format("This should not have been invoked with %s, %s", duration, payload));
          }
        };

    final BiConsumer<Duration, String> turnOffMethod =
        new BiConsumer<>() {
          @Override
          public void accept(final Duration duration, final String payload) {}
        };

    WateringController.executeDay(now, timedActions, turnOnMethod, turnOffMethod);
  }

   */
}
