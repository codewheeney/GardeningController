package earth.cassidy.gardeningcontroller.schedule;

import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;

@Test
public class OperationListTest {
    public void testBuilder() {
        List<UUID> operationIds = List.of(UUID.randomUUID(), UUID.randomUUID());

        OperationList operationList = OperationList.builder()
                .operations(operationIds)
                .build();

        assertTrue(operationList.operations().contains(operationIds.get(0)));
        assertTrue(operationList.operations().contains(operationIds.get(1)));
    }
}
