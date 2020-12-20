package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.exception.SeriesErrorMessages;
import com.technoWeb.Tp.exception.UnauthorizedException;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeriesMapper seriesMapper;

    public List<Series> findAll() {
        List<SeriesEntity> seriesEntities = seriesRepository.findAll();
        if (!seriesEntities.isEmpty())
            return seriesEntities.stream()
                    .map(seriesMapper::toModel)
                    .collect(Collectors.toList());
        else
            throw new NoContentException(SeriesErrorMessages.NO_SERIES.name());
    }

    public Series findById(long id) {
        return seriesRepository.findById(id).map(seriesMapper::toModel)
                .orElseThrow(() -> new NotFoundException(SeriesErrorMessages.SERIES_NOT_FOUND.name()));
    }

    public Series create(Series series) {
        SeriesEntity seriesEntity = seriesMapper.fromModel(series);
        long id = series.getId();
        if(id == 0) {
            seriesEntity.setId(id);
            try {
                return seriesMapper.toModel(seriesRepository.save(seriesEntity));
            }
            catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                throw new UnauthorizedException(SeriesErrorMessages.SERIES_DUPLICATED_TITLE.name());
            }
        } else {
            throw new UnauthorizedException(SeriesErrorMessages.SERIES_ALREADY_EXIST.name());
        }
    }

    public Series update(Series series) {
        SeriesEntity seriesEntity = seriesMapper.fromModel(series);
        long id = series.getId();
        if(id != 0) {
            seriesEntity.setId(id);
            try {
                return seriesMapper.toModel(seriesRepository.save(seriesEntity));
            }
            catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                throw new UnauthorizedException(SeriesErrorMessages.SERIES_DUPLICATED_TITLE.name());
            }
        } else {
            throw new UnauthorizedException(SeriesErrorMessages.NEW_SERIES.name());
        }
    }

    public Series delete(long id) {
        Optional<SeriesEntity> seriesEntityOptional = seriesRepository.findById(id);
        if (seriesEntityOptional.isPresent()) {
            SeriesEntity seriesEntity = seriesEntityOptional.get();
            seriesRepository.deleteById(id);
            return seriesMapper.toModel(seriesEntity);
        } else
            throw new NotFoundException(SeriesErrorMessages.SERIES_NOT_FOUND.name());
    }

}
