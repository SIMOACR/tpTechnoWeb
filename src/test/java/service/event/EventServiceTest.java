package service.event;


import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.model.Event;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.model.User;
import com.technoWeb.Tp.repository.EventRepository;
import com.technoWeb.Tp.service.event.EventEntity;
import com.technoWeb.Tp.service.event.EventMapper;
import com.technoWeb.Tp.service.event.EventService;
import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.user.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {


    @Mock
    EventRepository eventRepository;

    @Mock
    EventMapper eventMapper;

    @InjectMocks
    EventService eventService;

    LocalDateTime date = LocalDateTime.of(2020, 5, 10,0,0);
    User user = new User("anouazzani", "anouazzani", "anass", "ouazzani");
    UserEntity userEntity = new UserEntity(1, "anouazzani", "anouazzani","anass","ouazzani");
    private SeriesEntity seriesEntity = new SeriesEntity(1,"titleSerie", "desc", true, userEntity);
    private Series series = new Series(1, "titleSerie", "desc", true, user);


    @Before
    public void setup() {
        float value = 10;
        float value2 = 20;
        when(eventMapper.toModel(new EventEntity(1,date,value,"CommentTest",seriesEntity,null))).thenReturn(new Event(1, date, 10, "CommentTest", series, null));
        when(eventMapper.toModel(new EventEntity(2,date,value2,"CommentTest2",seriesEntity,null))).thenReturn(new Event(2, date, 20, "CommentTest2", series, null));
        when(eventMapper.fromModel(new Event(1, date, 10, "CommentTest", series, null))).thenReturn(new EventEntity(1,date,value,"CommentTest",seriesEntity,null));


        when(eventRepository.findAll()).thenReturn(
                new ArrayList<EventEntity>(
                        Arrays.asList(new EventEntity(1,date,value,"CommentTest",seriesEntity,null), new EventEntity( 2,date,value2,"CommentTest2",seriesEntity,null))
                )
        );


        when(eventRepository.findById(1L)).thenReturn(Optional.of(new EventEntity(1,date,value,"CommentTest",seriesEntity,null)));
        when(eventRepository.findById(3L)).thenReturn(Optional.empty());

        when(eventRepository.save(new EventEntity(1,date,value,"CommentTest",seriesEntity,null))).thenReturn(new EventEntity(1,date,value,"CommentTest",seriesEntity,null));

    }

    @Test
    public void when_getAll_expect_events() throws Exception {
        Assertions.assertTrue(eventService.findAll().size() == 2);
    }

    @Test
    public void when_getAllNonExisting_expect_204() throws Exception {
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NoContentException.class, () -> eventService.findAll());
    }

    @Test
    public void when_findById_expect_event() throws Exception{
        Assertions.assertEquals(10, eventService.findById(1L).getValue());
    }

    @Test
    public void when_getByIdNonExisting_expect_404() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> eventService.findById(3L));
    }

    @Test
    public void when_update_expect_tag() throws Exception{
        Event e = eventService.update(new Event(1, date, 10, "CommentTest", series, null));
        Assertions.assertEquals(10, e.getValue());
    }

    @Test
    public void when_delete_expect_tag() throws Exception{
        Event e = eventService.delete(1L);
        verify(eventRepository).deleteById(1L);
        Assertions.assertEquals(10, e.getValue());
    }

    @Test
    public void when_deleteNonExisting_expect_404() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> eventService.delete(2L));
    }




}
