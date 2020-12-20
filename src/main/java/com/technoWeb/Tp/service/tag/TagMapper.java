package com.technoWeb.Tp.service.tag;

import com.technoWeb.Tp.model.Tag;
import com.technoWeb.Tp.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements Mapper<Tag, TagEntity> {
    @Override
    public Tag toModel(TagEntity entity) {
        return new Tag(
                entity.getId(),
                entity.getName()
        );
    }

    @Override
    public TagEntity fromModel(Tag model) {
        return new TagEntity(
                model.getId(),
                model.getName()
        );
    }
}
