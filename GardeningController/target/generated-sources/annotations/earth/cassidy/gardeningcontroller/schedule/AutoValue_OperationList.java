package earth.cassidy.gardeningcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_OperationList extends OperationList {

  private final List<UUID> operations;

  private AutoValue_OperationList(
      List<UUID> operations) {
    this.operations = operations;
  }

  @JsonProperty("operations")
  @Override
  public List<UUID> operations() {
    return operations;
  }

  @Override
  public String toString() {
    return "OperationList{"
        + "operations=" + operations
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof OperationList) {
      OperationList that = (OperationList) o;
      return this.operations.equals(that.operations());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= operations.hashCode();
    return h$;
  }

  static final class Builder extends OperationList.Builder {
    private List<UUID> operations;
    Builder() {
    }
    @Override
    OperationList.Builder operations(List<UUID> operations) {
      if (operations == null) {
        throw new NullPointerException("Null operations");
      }
      this.operations = operations;
      return this;
    }
    @Override
    OperationList build() {
      String missing = "";
      if (this.operations == null) {
        missing += " operations";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_OperationList(
          this.operations);
    }
  }

}
