package earth.cassidy.gardeningcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.time.Duration;
import java.time.LocalTime;

@AutoValue
@JsonDeserialize(builder = AutoValue_TimedAction.Builder.class)
public abstract class TimedAction {
  @JsonProperty("startTime")
  public abstract LocalTime startTime();

  @JsonProperty("duration")
  public abstract Duration duration();

  @JsonProperty("startAction")
  public abstract String startAction();

  @JsonProperty("endAction")
  public abstract String endAction();

  public static Builder builder() {
    return new AutoValue_TimedAction.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract TimedAction build();

    @JsonProperty("startTime")
    public abstract Builder startTime(LocalTime startTime);

    @JsonProperty("duration")
    public abstract Builder duration(Duration duration);

    @JsonProperty("startAction")
    public abstract Builder startAction(String startAction);

    @JsonProperty("endAction")
    public abstract Builder endAction(String endAction);
  }
}
