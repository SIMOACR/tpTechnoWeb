package service.event;

import com.technoWeb.Tp.service.event.EventEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventEntityTest {

    @Test
    void cannotBuildWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EventEntity(null);
        });
    }


}
