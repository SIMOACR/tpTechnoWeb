package com.technoWeb.Tp.repository;

import com.technoWeb.Tp.service.series.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {
}
