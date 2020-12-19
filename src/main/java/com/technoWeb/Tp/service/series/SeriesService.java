package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.exception.SeriesErrorMessages;
import com.technoWeb.Tp.exception.UnauthorizedException;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    private SeriesRepository seriesRepository;

    private SeriesMapper seriesMapper;

    public SeriesService(SeriesRepository seriesRepository, SeriesMapper seriesMapper) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
    }

    public List<Series> findAll() {
        List<SeriesEntity> facilityEntities = seriesRepository.findAll();
        if (!facilityEntities.isEmpty())
            return facilityEntities.stream()
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
            return seriesMapper.toModel(seriesRepository.save(seriesEntity));
        } else {
            throw new UnauthorizedException(SeriesErrorMessages.SERIES_ALREADY_EXIST.name());
        }
    }

    public Series update(Series series) {
        SeriesEntity seriesEntity = seriesMapper.fromModel(series);
        long id = series.getId();
        if(id != 0) {
            seriesEntity.setId(id);
            return seriesMapper.toModel(seriesRepository.save(seriesEntity));
        } else {
            throw new UnauthorizedException(SeriesErrorMessages.NEW_SERIES.name());
        }
    }

    public Series delete(long id) {
        Optional<SeriesEntity> facilityEntityOptional = seriesRepository.findById(id);
        if (facilityEntityOptional.isPresent()) {
            SeriesEntity facilityEntity = facilityEntityOptional.get();
            seriesRepository.deleteById(id);
            return seriesMapper.toModel(facilityEntity);
        } else
            throw new NotFoundException(SeriesErrorMessages.SERIES_NOT_FOUND.name());
    }

}
