package earth.cassidy.gardeningcontroller.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import earth.cassidy.gardeningcontroller.BaseTest;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import org.testng.annotations.Test;

@Test
public class DailyScheduleTest extends BaseTest {
  @Test
  public void testBuilder() {
    final WateringTime wt1 =
        WateringTime.builder()
            .startTime(LocalTime.of(9, 0))
            .duration(Duration.ofMinutes(15))
            .build();

    final WateringTime wt2 =
        WateringTime.builder()
            .startTime(LocalTime.of(18, 0))
            .duration(Duration.ofMinutes(15))
            .build();

    final DailySchedule dailySchedule =
        DailySchedule.builder()
            .mondayTimes(List.of(wt1, wt2))
            .tuesdayTimes(List.of(wt1, wt2))
            .wednesdayTimes(List.of(wt1, wt2))
            .thursdayTimes(List.of(wt1, wt2))
            .fridayTimes(List.of(wt1, wt2))
            .saturdayTimes(List.of(wt1, wt2))
            .sundayTimes(List.of(wt1, wt2))
            .build();

    assertThat(dailySchedule.mondayTimes()).contains(wt1, wt2);
    assertThat(dailySchedule.tuesdayTimes()).contains(wt1, wt2);
    assertThat(dailySchedule.wednesdayTimes()).contains(wt1, wt2);
    assertThat(dailySchedule.thursdayTimes()).contains(wt1, wt2);
    assertThat(dailySchedule.fridayTimes()).contains(wt1, wt2);
    assertThat(dailySchedule.saturdayTimes()).contains(wt1, wt2);
    assertThat(dailySchedule.sundayTimes()).contains(wt1, wt2);
  }

  @Test
  public void testSerialization() throws JsonProcessingException {
    final WateringTime wt1 =
        WateringTime.builder()
            .startTime(LocalTime.of(9, 0))
            .duration(Duration.ofMinutes(15))
            .build();

    final WateringTime wt2 =
        WateringTime.builder()
            .startTime(LocalTime.of(18, 0))
            .duration(Duration.ofMinutes(15))
            .build();

    final DailySchedule dailySchedule =
        DailySchedule.builder()
            .mondayTimes(List.of(wt1, wt2))
            .tuesdayTimes(List.of(wt1, wt2))
            .wednesdayTimes(List.of(wt1, wt2))
            .thursdayTimes(List.of(wt1, wt2))
            .fridayTimes(List.of(wt1, wt2))
            .saturdayTimes(List.of(wt1, wt2))
            .sundayTimes(List.of(wt1, wt2))
            .build();

    final String json = objectMapper.writeValueAsString(dailySchedule);

    final DailySchedule deserialized = objectMapper.readValue(json, DailySchedule.class);
    assertThat(deserialized).isEqualTo(dailySchedule);
  }
}
