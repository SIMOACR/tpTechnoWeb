package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.exception.SeriesErrorMessages;
import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .orElseThrow(() -> new NotFoundException(SeriesErrorMessages.SERIES_NOT_FOUND.name()));
    }

    public Series createOrUpdate(Series facility) {
        SeriesEntity facilityEntity = seriesMapper.fromModel(facility);
        Long id = facility.getId();
        if(id != null)
            facilityEntity.setId(id);
        return seriesMapper.toModel(seriesRepository.save(facilityEntity));
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
