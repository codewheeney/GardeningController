package earth.cassidy.gardeningcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
@JsonDeserialize(builder = AutoValue_DailySchedule.Builder.class)
public abstract class DailySchedule {
  @JsonProperty("mondayTimes")
  public abstract List<WateringTime> mondayTimes();

  @JsonProperty("tuesdayTimes")
  public abstract List<WateringTime> tuesdayTimes();

  @JsonProperty("wednesdayTimes")
  public abstract List<WateringTime> wednesdayTimes();

  @JsonProperty("thursdayTimes")
  public abstract List<WateringTime> thursdayTimes();

  @JsonProperty("fridayTimes")
  public abstract List<WateringTime> fridayTimes();

  @JsonProperty("saturdayTimes")
  public abstract List<WateringTime> saturdayTimes();

  @JsonProperty("sundayTimes")
  public abstract List<WateringTime> sundayTimes();

  public static Builder builder() {
    return new AutoValue_DailySchedule.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract DailySchedule build();

    @JsonProperty("mondayTimes")
    public abstract Builder mondayTimes(List<WateringTime> mondayTimes);

    @JsonProperty("tuesdayTimes")
    public abstract Builder tuesdayTimes(List<WateringTime> tuesdayTimes);

    @JsonProperty("wednesdayTimes")
    public abstract Builder wednesdayTimes(List<WateringTime> wednesdayTimes);

    @JsonProperty("thursdayTimes")
    public abstract Builder thursdayTimes(List<WateringTime> thursdayTimes);

    @JsonProperty("fridayTimes")
    public abstract Builder fridayTimes(List<WateringTime> fridayTimes);

    @JsonProperty("saturdayTimes")
    public abstract Builder saturdayTimes(List<WateringTime> saturdayTimes);

    @JsonProperty("sundayTimes")
    public abstract Builder sundayTimes(List<WateringTime> sundayTimes);
  }
}
