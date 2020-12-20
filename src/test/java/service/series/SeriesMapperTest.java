package service.series;

import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.model.User;
import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.series.SeriesMapper;
import com.technoWeb.Tp.service.user.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SeriesMapperTest {
    /*
    private SeriesMapper seriesMapper = new SeriesMapper();

    User user = new User("anouazzani", "anouazzani", "anass", "ouazzani");
    UserEntity userEntity = new UserEntity(1, "anouazzani", "anouazzani","anass","ouazzani");

    private Series SERIES = new Series(1,"testTitle","desc", true, user);
    private SeriesEntity SERIESENTITY = new SeriesEntity(1,"testTitle","desc", true, userEntity);

    @Test
    public void when_toModel_expect_series() {
        Assertions.assertEquals(SERIES, seriesMapper.toModel(SERIESENTITY));
    }

    @Test
    public void when_fromModel_expect_seriesEntity() {
        Assertions.assertEquals(SERIESENTITY.getId(), seriesMapper.fromModel(SERIES).getId());
        Assertions.assertEquals(SERIESENTITY.getTitle(), seriesMapper.fromModel(SERIES).getTitle());
        Assertions.assertEquals(SERIESENTITY.getDescription(), seriesMapper.fromModel(SERIES).getDescription());
    }
    */
}
