package earth.cassidy.gardeningcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.time.Duration;
import java.time.LocalTime;

@AutoValue
@JsonDeserialize(builder = AutoValue_WateringTime.Builder.class)
public abstract class WateringTime {
  @JsonProperty("startTime")
  public abstract LocalTime startTime();

  @JsonProperty("duration")
  public abstract Duration duration();

  public static Builder builder() {
    return new AutoValue_WateringTime.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract WateringTime build();

    @JsonProperty("startTime")
    public abstract Builder startTime(LocalTime startTime);

    @JsonProperty("duration")
    public abstract Builder duration(Duration duration);
  }
}
