package com.technoWeb.Tp.repository;

import com.technoWeb.Tp.service.series.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {
    Optional<SeriesEntity> findById(long id);
}
