package service.tag;

import com.technoWeb.Tp.service.tag.TagEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagEntityTest {

    @Test
    void cannotBuildWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TagEntity(null);
        });
    }

    @Test
    void cannotBuildWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TagEntity("");
        });
    }

    @Test
    void cannotBuildWithBlankName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TagEntity("    ");
        });
    }

    @Test
    void cannotBuildWithNameStartingWithDigit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TagEntity("1ROLE");
        });
    }

    @Test
    void cannotBuildWithNameContainingSpaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TagEntity("THE tag");
        });
    }

    @Test
    void mustReturnItsName() {
        assertThat(new TagEntity("tag").getName()).isEqualTo("tag");
    }

    @Test
    void mustNotBeEqualToNull() {
        assertThat(new TagEntity("tag").equals(null)).isFalse();
    }


}
