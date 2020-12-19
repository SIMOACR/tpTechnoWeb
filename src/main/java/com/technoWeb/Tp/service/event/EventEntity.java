package com.technoWeb.Tp.service.event;

import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.tag.TagEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    private Timestamp timestamp;

    @NonNull
    private float value;

    private String comment;

    @NonNull
    @ManyToOne
    private SeriesEntity seriesEntity;

    @ManyToMany
    private List<TagEntity> tagEntityList;

    public EventEntity(Timestamp timestamp, float value, String comment, SeriesEntity fromModel, List<TagEntity> tagEntityList) {
    }
}
