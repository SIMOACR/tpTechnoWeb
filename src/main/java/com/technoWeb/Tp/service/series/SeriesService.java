package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.SeriesErrorMessages;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final SeriesMapper seriesMapper;

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
                .orElseThrow(() -> new NoContentException(SeriesErrorMessages.SERIES_NOT_FOUND.name()));
    }

}
