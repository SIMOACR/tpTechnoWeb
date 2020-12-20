package com.technoWeb.Tp.service.tag;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.exception.TagErrorMessages;
import com.technoWeb.Tp.exception.UnauthorizedException;
import com.technoWeb.Tp.model.Tag;
import com.technoWeb.Tp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    public List<Tag> findAll() {
        List<TagEntity> tagEntities = tagRepository.findAll();
        if (!tagEntities.isEmpty())
            return tagEntities.stream()
                    .map(tagMapper::toModel)
                    .collect(Collectors.toList());
        else
            throw new NoContentException(TagErrorMessages.NO_TAGS.name());
    }

    public Tag findById(long id) {
        return tagRepository.findById(id).map(tagMapper::toModel)
                .orElseThrow(() -> new NotFoundException(TagErrorMessages.TAG_NOT_FOUND.name()));
    }

    public Tag create(Tag tag) {
        TagEntity tagEntity = tagMapper.fromModel(tag);
        long id = tag.getId();
        if(id == 0) {
            tagEntity.setId(id);
            try {
                return tagMapper.toModel(tagRepository.save(tagEntity));
            }
            catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                throw new UnauthorizedException(TagErrorMessages.TAG_DUPLICATED_NAME.name());
            }
        } else {
            throw new UnauthorizedException(TagErrorMessages.TAG_ALREADY_EXIST.name());
        }
    }

    public Tag update(Tag tag) {
        TagEntity tagEntity = tagMapper.fromModel(tag);
        long id = tag.getId();
        if(id != 0) {
            tagEntity.setId(id);
            try {
                return tagMapper.toModel(tagRepository.save(tagEntity));
            }
            catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                throw new UnauthorizedException(TagErrorMessages.TAG_DUPLICATED_NAME.name());
            }
        } else {
            throw new UnauthorizedException(TagErrorMessages.NEW_TAG.name());
        }
    }


    public Tag delete(long id) {
        Optional<TagEntity> tagEntityOptional = tagRepository.findById(id);
        if (tagEntityOptional.isPresent()) {
            TagEntity tagEntity = tagEntityOptional.get();
            tagRepository.deleteById(id);
            return tagMapper.toModel(tagEntity);
        } else
            throw new NotFoundException(TagErrorMessages.TAG_NOT_FOUND.name());
    }

}
