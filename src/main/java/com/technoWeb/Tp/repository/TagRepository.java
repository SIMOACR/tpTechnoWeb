package com.technoWeb.Tp.repository;

import com.technoWeb.Tp.service.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findById(long id);
    Optional<TagEntity> findByName(String name);
}
