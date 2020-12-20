package com.technoWeb.Tp.service.event;

import com.technoWeb.Tp.model.Event;
import com.technoWeb.Tp.model.Tag;
import com.technoWeb.Tp.service.mapper.Mapper;
import com.technoWeb.Tp.service.series.SeriesMapper;
import com.technoWeb.Tp.service.tag.TagEntity;
import com.technoWeb.Tp.service.tag.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper implements Mapper<Event, EventEntity> {
    @Autowired
    private SeriesMapper seriesMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Event toModel(EventEntity entity) {
        List<Tag> tagList;
        if (entity.getTagEntityList() == null)
            tagList = null;
        else
            tagList = entity.getTagEntityList().stream()
                    .map(tagMapper::toModel)
                    .collect(Collectors.toList());
        return new Event(
            entity.getId(),
            entity.getEventDate(),
            entity.getValue(),
            entity.getComment(),
            seriesMapper.toModel(entity.getSeriesEntity()),
            tagList
        );
    }

    @Override
    public EventEntity fromModel(Event model) {
        List<TagEntity> tagEntityList;
        if (model.getTagList() == null)
            tagEntityList = null;
        else
            tagEntityList = model.getTagList().stream()
                    .map(tagMapper::fromModel)
                    .collect(Collectors.toList());
        return new EventEntity(
                model.getId(),
                model.getEventDate(),
                model.getValue(),
                model.getComment(),
                seriesMapper.fromModel(model.getSeries()),
                tagEntityList
        );
    }
}
