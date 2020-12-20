package service.series;


import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.model.User;
import com.technoWeb.Tp.repository.SeriesRepository;
import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.series.SeriesMapper;
import com.technoWeb.Tp.service.series.SeriesService;
import com.technoWeb.Tp.service.user.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeriesServiceTest {

    @Mock
    SeriesRepository seriesRepository;

    @Mock
    SeriesMapper seriesMapper;

    @InjectMocks
    SeriesService seriesService;

    UserEntity userEntity = new UserEntity(1, "anouazzani", "anouazzani","anass","ouazzani");
    User user = new User("anouazzani", "anouazzani", "anass", "ouazzani");


    @Before
    public void setup() {
        when(seriesMapper.toModel(new SeriesEntity(1,"TitleTest", "DescTest", true, userEntity))).thenReturn(new Series(1,"TitleTest", "DescTest", true, user));
        when(seriesMapper.toModel(new SeriesEntity(2,"TitleTest2", "DescTest2", true, userEntity))).thenReturn(new Series(2,"TitleTest2", "DescTest2", true, user));
        when(seriesMapper.fromModel(new Series(1,"TitleTest", "DescTest", true,user))).thenReturn(new SeriesEntity(1,"TitleTest", "DescTest", true, userEntity));


        when(seriesRepository.findAll()).thenReturn(
                new ArrayList<SeriesEntity>(
                        Arrays.asList(new SeriesEntity(1,"TitleTest", "DescTest", true, userEntity), new SeriesEntity( 2,"TitleTest2", "DescTest2", true, userEntity))
                )
        );


        when(seriesRepository.findById(1L)).thenReturn(Optional.of(new SeriesEntity(1,"TitleTest", "DescTest", true, userEntity)));
        when(seriesRepository.findById(3L)).thenReturn(Optional.empty());

        when(seriesRepository.save(new SeriesEntity(1,"TitleTest", "DescTest", true, userEntity))).thenReturn(new SeriesEntity(1,"TitleTest", "DescTest", true, userEntity));

    }

    @Test
    public void when_getAll_expect_series() throws Exception {
        Assertions.assertTrue(seriesService.findAll().size() == 2);
    }

    @Test
    public void when_getAllNonExisting_expect_204() throws Exception {
        when(seriesRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NoContentException.class, () -> seriesService.findAll());
    }

    @Test
    public void when_findById_expect_facility() throws Exception{
        Assertions.assertEquals("TitleTest", seriesService.findById(1L).getTitle());
    }

    @Test
    public void when_getByIdNonExisting_expect_404() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> seriesService.findById(3L));
    }

    @Test
    public void when_update_expect_series() throws Exception{
        Series t = seriesService.update(new Series(1,"TitleTest", "DescTest", true, user));
        Assertions.assertEquals("TitleTest", t.getTitle());
    }

    @Test
    public void when_delete_expect_tag() throws Exception{
        Series t = seriesService.delete(1L);
        verify(seriesRepository).deleteById(1L);
        Assertions.assertEquals("TitleTest", t.getTitle());
    }

    @Test
    public void when_deleteNonExisting_expect_404() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> seriesService.delete(2L));
    }

}
