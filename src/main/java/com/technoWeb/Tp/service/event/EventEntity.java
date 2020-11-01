package com.technoWeb.Tp.service.event;

import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.tag.TagEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime date;

    @NonNull
    private float value;

    private String comment;

    @NonNull
    @ManyToOne
    private SeriesEntity seriesEntity;

    @ManyToMany
    private List<TagEntity> tagEntityList;
}
