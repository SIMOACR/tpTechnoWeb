package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SeriesMapper implements Mapper<Series, SeriesEntity> {

    @Override
    public Series toModel(SeriesEntity entity) {
        return new Series(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isPublicAccess()
        );
    }

    @Override
    public SeriesEntity fromModel(Series model) {
        return new SeriesEntity(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.isPublicAccess()
        );
    }
}
