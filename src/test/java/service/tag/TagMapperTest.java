package service.tag;

import com.technoWeb.Tp.model.Tag;
import com.technoWeb.Tp.service.tag.TagEntity;
import com.technoWeb.Tp.service.tag.TagMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TagMapperTest {

    private TagMapper tagMapper = new TagMapper();

    private Tag TAG = new Tag(1,"testName");
    private TagEntity TAGENTITY = new TagEntity(1,"testName");

    @Test
    public void when_toModel_expect_tag() {
        Assertions.assertEquals(TAG, tagMapper.toModel(TAGENTITY));
    }

    @Test
    public void when_fromModel_expect_tagEntity() {
        Assertions.assertEquals(TAGENTITY.getId(), tagMapper.fromModel(TAG).getId());
        Assertions.assertEquals(TAGENTITY.getName(), tagMapper.fromModel(TAG).getName());
    }

}
