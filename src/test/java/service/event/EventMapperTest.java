package service.event;

import com.technoWeb.Tp.model.Event;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.model.User;
import com.technoWeb.Tp.service.event.EventEntity;
import com.technoWeb.Tp.service.event.EventMapper;
import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.series.SeriesMapper;
import com.technoWeb.Tp.service.user.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class EventMapperTest {
    /*
    @Mock
    private SeriesMapper seriesMapper;

    private EventMapper eventMapper = new EventMapper();
    LocalDateTime date = LocalDateTime.of(2020, 3, 10,0,0);
    User user = new User("anouazzani", "anouazzani", "anass", "ouazzani");
    UserEntity userEntity = new UserEntity("anouazzani", "anouazzani", "anass", "ouazzani");
    private Series series = new Series(1, "titleSerie", "desc", true, user);
    private SeriesEntity seriesEntity = new SeriesEntity(1,"titleSerie", "desc", true, userEntity);
    private Event EVENT = new Event(1, date, 10, "Comment", series, null);
    float value = 10;
    private EventEntity EVENTENTITY = new EventEntity(1, date, value, "Comment", seriesEntity, null);

    @Before
    public void setup() {
        when(seriesMapper.toModel(seriesEntity)).thenReturn(series);
        when(seriesMapper.fromModel(series)).thenReturn(seriesEntity);
    }

    @Test
    public void when_toModel_expect_event() {
        Assertions.assertEquals(EVENT, eventMapper.toModel(EVENTENTITY));
    }

    @Test
    public void when_fromModel_expect_eventEntity() {
        Assertions.assertEquals(EVENTENTITY.getValue(), eventMapper.fromModel(EVENT).getValue());
    }
    */

}
