package earth.cassidy.gardeningcontroller.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import earth.cassidy.gardeningcontroller.BaseTest;
import java.time.Duration;
import java.time.LocalTime;
import org.testng.annotations.Test;

@Test
public class WateringTimeTest extends BaseTest {
  @Test
  public void testBuilder() {
    final LocalTime startTime = LocalTime.of(8, 0, 0);
    final Duration duration = Duration.ofMinutes(15);
    final WateringTime wateringTime =
        WateringTime.builder().startTime(startTime).duration(duration).build();

    assertThat(wateringTime.startTime()).isEqualTo(startTime);
    assertThat(wateringTime.duration()).isEqualTo(duration);
  }

  @Test
  public void testSerialization() throws JsonProcessingException {
    final LocalTime startTime = LocalTime.of(10, 30, 0);
    final Duration duration = Duration.ofMinutes(45);
    final WateringTime wateringTime =
        WateringTime.builder().startTime(startTime).duration(duration).build();

    final String json = objectMapper.writeValueAsString(wateringTime);

    final WateringTime deserialized = objectMapper.readValue(json, WateringTime.class);
    assertThat(deserialized).isEqualTo(wateringTime);
  }
}
