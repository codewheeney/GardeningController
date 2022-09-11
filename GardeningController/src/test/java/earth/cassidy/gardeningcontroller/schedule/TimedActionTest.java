package earth.cassidy.gardeningcontroller.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import earth.cassidy.gardeningcontroller.BaseTest;
import java.time.Duration;
import java.time.LocalTime;
import org.testng.annotations.Test;

@Test
public class TimedActionTest extends BaseTest {
  @Test
  public void testBuilder() {
    final LocalTime startTime = LocalTime.of(8, 0, 0);
    final Duration duration = Duration.ofMinutes(15);
    final TimedAction timedAction =
        TimedAction.builder()
            .startTime(startTime)
            .duration(duration)
            .startAction("foo")
            .endAction("foo")
            .build();

    assertThat(timedAction.startTime()).isEqualTo(startTime);
    assertThat(timedAction.duration()).isEqualTo(duration);
  }

  @Test
  public void testSerialization() throws JsonProcessingException {
    final LocalTime startTime = LocalTime.of(10, 30, 0);
    final Duration duration = Duration.ofMinutes(45);
    final TimedAction timedAction =
        TimedAction.builder()
            .startTime(startTime)
            .duration(duration)
            .startAction("foo")
            .endAction("foo")
            .build();

    final String json = objectMapper.writeValueAsString(timedAction);

    final TimedAction deserialized = objectMapper.readValue(json, TimedAction.class);
    assertThat(deserialized).isEqualTo(timedAction);
  }
}
