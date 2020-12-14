package earth.cassidy.gardeningcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.util.List;
import java.util.UUID;

@AutoValue
@JsonDeserialize(builder = AutoValue_OperationList.Builder.class)
public abstract class OperationList {
    @JsonProperty("operations")
    public abstract List<UUID> operations();

    static Builder builder() {
        return new AutoValue_OperationList.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
        @JsonProperty("operations")
        abstract Builder operations(List<UUID> operations);

        abstract OperationList build();
    }
}
