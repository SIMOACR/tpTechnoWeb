package service.series;

/*import com.technoWeb.Tp.service.series.SeriesEntity;*/

import com.technoWeb.Tp.service.series.SeriesEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeriesEntityTest {


    @Test
    void cannotBuildWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SeriesEntity(null);
        });
    }

    @Test
    void cannotBuildWithEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SeriesEntity("");
        });
    }

    @Test
    void cannotBuildWithBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SeriesEntity("    ");
        });
    }

    @Test
    void cannotBuildWithTitleStartingWithDigit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SeriesEntity("1TITLE");
        });
    }

    @Test
    void cannotBuildWithNameContainingSpaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SeriesEntity("THE TITLE");
        });
    }

    @Test
    void mustReturnItsTitle() {
        assertThat(new SeriesEntity("anass").getTitle()).isEqualTo("anass");
    }

    @Test
    void mustNotBeEqualToNull() {
        assertThat(new SeriesEntity("anass").equals(null)).isFalse();
    }

}
