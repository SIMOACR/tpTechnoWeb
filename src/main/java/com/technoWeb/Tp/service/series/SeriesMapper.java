package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.service.mapper.Mapper;
import com.technoWeb.Tp.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeriesMapper implements Mapper<Series, SeriesEntity> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Series toModel(SeriesEntity entity) {
        return new Series(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isPublicAccess(),
                userMapper.toModel(entity.getUserEntity())
        );
    }

    @Override
    public SeriesEntity fromModel(Series model) {
        return new SeriesEntity(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.isPublicAccess(),
                userMapper.fromModel(model.getUser())
        );
    }
}
