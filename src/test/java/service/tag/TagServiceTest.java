package service.tag;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.model.Tag;
import com.technoWeb.Tp.repository.TagRepository;
import com.technoWeb.Tp.service.tag.TagEntity;
import com.technoWeb.Tp.service.tag.TagMapper;
import com.technoWeb.Tp.service.tag.TagService;
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
public class TagServiceTest {

    @Mock
    TagRepository tagRepository;

    @Mock
    TagMapper tagMapper;

    @InjectMocks
    TagService tagService;

    @Before
    public void setup() {
        when(tagMapper.toModel(new TagEntity(1,"NameTest"))).thenReturn(new Tag(1,"NameTest"));
        when(tagMapper.toModel(new TagEntity(2,"NameTest2"))).thenReturn(new Tag(2,"NameTest2"));
        when(tagMapper.fromModel(new Tag(1,"NameTest"))).thenReturn(new TagEntity(1,"NameTest"));


        when(tagRepository.findAll()).thenReturn(
                new ArrayList<TagEntity>(
                        Arrays.asList(new TagEntity(1, "NameTest"), new TagEntity( 2,"NameTest2"))
                )
        );


        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity(1,"NameTest")));
        when(tagRepository.findById(3L)).thenReturn(Optional.empty());

        when(tagRepository.save(new TagEntity(1,"NameTest"))).thenReturn(new TagEntity(1,"NameTest"));

    }

    @Test
    public void when_getAll_expect_tags() throws Exception {
        Assertions.assertTrue(tagService.findAll().size() == 2);
    }

    @Test
    public void when_getAllNonExisting_expect_204() throws Exception {
        when(tagRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NoContentException.class, () -> tagService.findAll());
    }

    @Test
    public void when_findById_expect_facility() throws Exception{
        Assertions.assertEquals("NameTest", tagService.findById(1L).getName());
    }

    @Test
    public void when_getByIdNonExisting_expect_404() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> tagService.findById(3L));
    }

    @Test
    public void when_update_expect_tag() throws Exception{
        Tag t = tagService.update(new Tag(1,"NameTest"));
        Assertions.assertEquals("NameTest", t.getName());
    }

    @Test
    public void when_delete_expect_tag() throws Exception{
        Tag t = tagService.delete(1L);
        verify(tagRepository).deleteById(1L);
        Assertions.assertEquals("NameTest", t.getName());
    }

    @Test
    public void when_deleteNonExisting_expect_404() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> tagService.delete(2L));
    }






}
