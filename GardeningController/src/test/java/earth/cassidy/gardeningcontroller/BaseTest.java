package earth.cassidy.gardeningcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BaseTest {
  protected static final ObjectMapper objectMapper =
      JsonMapper.builder().addModule(new JavaTimeModule()).build();
}
