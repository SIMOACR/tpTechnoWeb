package com.technoWeb.Tp.repository;

import com.technoWeb.Tp.service.event.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findById(long id);
    Optional<List<EventEntity>> findBySeriesEntityUserEntityUserNameAndTagEntityList_Name(String userName, String tagName);
    Optional<List<EventEntity>> findAllBySeriesEntityId(long id);
    Long countAllByEventDateBetweenAndTagEntityList_NameAndSeriesEntityUserEntityUserName(
            LocalDateTime timestamp1, LocalDateTime timestamp, String tagName, String userName
    );

    @Query(value = "SELECT MAX(e.event_date) " +
            "  FROM event e" +
            "  INNER JOIN series s ON e.series_entity_id = s.id" +
            "  INNER JOIN user u ON s.user_entity_id = u.id" +
            "  INNER JOIN event_tag_entity_list et ON e.id = et.event_id" +
            "  INNER JOIN tag t ON et.tag_entity_list_id = t.id" +
            "  WHERE u.user_name = ?1 " +
            "  AND t.name = ?2",
            nativeQuery = true
    )
    LocalDateTime findLatestOccurrence(String userName, String tagName);
}
