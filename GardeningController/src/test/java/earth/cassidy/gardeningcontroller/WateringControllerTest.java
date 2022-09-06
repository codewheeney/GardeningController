package earth.cassidy.gardeningcontroller;

import static org.assertj.core.api.Assertions.assertThat;

import earth.cassidy.gardeningcontroller.schedule.WateringTime;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;
import org.assertj.core.api.Fail;
import org.testng.annotations.Test;

@Test
public class WateringControllerTest {
  @Test
  public void testExecuteDayMatches() {
    final LocalTime now = LocalTime.now();
    final LocalDate date = LocalDate.now();
    final Duration duration = Duration.ofMinutes(15);

    final List<WateringTime> wateringTimes =
        List.of(
            WateringTime.builder()
                .startTime(now.plus(Duration.ofHours(10)))
                .duration(duration.plusMinutes(1))
                .build(),
            WateringTime.builder().startTime(now).duration(duration).build(),
            WateringTime.builder()
                .startTime(now.minus(Duration.ofHours(10)))
                .duration(duration.minusMinutes(1))
                .build());

    final Consumer<Duration> testConsumer =
        new Consumer<>() {
          @Override
          public void accept(final Duration duration) {
            assertThat(duration).isEqualTo(duration);
          }
        };

    WateringController.executeDay(now, wateringTimes, testConsumer);
  }

  @Test
  public void testExecuteNoDayMatches() {
    final LocalTime now = LocalTime.now();
    final LocalDate date = LocalDate.now();
    final Duration duration = Duration.ofMinutes(15);

    final List<WateringTime> wateringTimes =
        List.of(
            WateringTime.builder()
                .startTime(now.plus(Duration.ofHours(10)))
                .duration(duration.plusMinutes(1))
                .build(),
            WateringTime.builder()
                .startTime(now.plus(duration.plusMinutes(1)))
                .duration(duration)
                .build(),
            WateringTime.builder()
                .startTime(now.minus(Duration.ofHours(10)))
                .duration(duration.minusMinutes(1))
                .build());

    final Consumer<Duration> testConsumer =
        new Consumer<>() {
          @Override
          public void accept(final Duration duration) {
            Fail.fail(String.format("This should not have been invoked with %s", duration));
          }
        };

    WateringController.executeDay(now, wateringTimes, testConsumer);
  }
}
