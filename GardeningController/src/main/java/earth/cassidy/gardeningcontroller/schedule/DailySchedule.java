package earth.cassidy.gardeningcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
@JsonDeserialize(builder = AutoValue_DailySchedule.Builder.class)
public abstract class DailySchedule {
  @JsonProperty("mondayTimes")
  public abstract List<TimedAction> mondayTimes();

  @JsonProperty("tuesdayTimes")
  public abstract List<TimedAction> tuesdayTimes();

  @JsonProperty("wednesdayTimes")
  public abstract List<TimedAction> wednesdayTimes();

  @JsonProperty("thursdayTimes")
  public abstract List<TimedAction> thursdayTimes();

  @JsonProperty("fridayTimes")
  public abstract List<TimedAction> fridayTimes();

  @JsonProperty("saturdayTimes")
  public abstract List<TimedAction> saturdayTimes();

  @JsonProperty("sundayTimes")
  public abstract List<TimedAction> sundayTimes();

  public static Builder builder() {
    return new AutoValue_DailySchedule.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract DailySchedule build();

    @JsonProperty("mondayTimes")
    public abstract Builder mondayTimes(List<TimedAction> mondayTimes);

    @JsonProperty("tuesdayTimes")
    public abstract Builder tuesdayTimes(List<TimedAction> tuesdayTimes);

    @JsonProperty("wednesdayTimes")
    public abstract Builder wednesdayTimes(List<TimedAction> wednesdayTimes);

    @JsonProperty("thursdayTimes")
    public abstract Builder thursdayTimes(List<TimedAction> thursdayTimes);

    @JsonProperty("fridayTimes")
    public abstract Builder fridayTimes(List<TimedAction> fridayTimes);

    @JsonProperty("saturdayTimes")
    public abstract Builder saturdayTimes(List<TimedAction> saturdayTimes);

    @JsonProperty("sundayTimes")
    public abstract Builder sundayTimes(List<TimedAction> sundayTimes);
  }
}
