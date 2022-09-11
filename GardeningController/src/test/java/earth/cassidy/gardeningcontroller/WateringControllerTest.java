package earth.cassidy.gardeningcontroller;

import static org.assertj.core.api.Assertions.assertThat;

import earth.cassidy.gardeningcontroller.schedule.DailySchedule;
import earth.cassidy.gardeningcontroller.schedule.TimedAction;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.BiConsumer;
import org.assertj.core.api.Fail;
import org.testng.annotations.Test;

@Test
public class WateringControllerTest {
  @Test
  public void testExecuteDayMatches() {
    final LocalTime now = LocalTime.now();
    final LocalDate date = LocalDate.now();
    final Duration duration = Duration.ofMinutes(15);
    final String startAction = "startAction";
    final String endAction = "endAction";

    final List<TimedAction> timedActions =
        List.of(
            TimedAction.builder()
                .startTime(now.plus(Duration.ofHours(10)))
                .duration(duration.plusMinutes(1))
                .startAction("foo")
                .endAction("foo")
                .build(),
            TimedAction.builder()
                .startTime(now)
                .duration(duration)
                .startAction(startAction)
                .endAction(endAction)
                .build(),
            TimedAction.builder()
                .startTime(now.minus(Duration.ofHours(10)))
                .duration(duration.minusMinutes(1))
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

    final BiConsumer<Duration, String> turnOnMethod =
        new BiConsumer<>() {
          @Override
          public void accept(final Duration duration, final String payload) {
            assertThat(duration).isEqualTo(duration);
            assertThat(payload).isEqualTo(startAction);
          }
        };

    final BiConsumer<Duration, String> turnOffMethod =
        new BiConsumer<>() {
          @Override
          public void accept(final Duration duration, final String payload) {
            assertThat(duration).isEqualTo(duration);
            assertThat(payload).isNotEqualTo(endAction);
          }
        };

    final WateringController wateringController =
        new WateringController(dailySchedule, turnOnMethod, turnOffMethod);

    wateringController.run();
  }

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
}
