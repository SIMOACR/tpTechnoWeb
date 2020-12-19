package com.technoWeb.Tp.repository;

import com.technoWeb.Tp.service.event.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findById(long id);
}
