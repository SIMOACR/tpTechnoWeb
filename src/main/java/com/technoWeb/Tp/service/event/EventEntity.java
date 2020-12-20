package com.technoWeb.Tp.service.event;

import com.technoWeb.Tp.service.series.SeriesEntity;
import com.technoWeb.Tp.service.tag.TagEntity;
import lombok.*;
import org.springframework.util.Assert;

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
    private LocalDateTime eventDate;

    @NonNull
    private Float value;

    public EventEntity(Float value) {
        Assert.notNull(value, "value cannot be null");

        this.value = value;
    }

    public float getValue(){
        return value;
    }

    private String comment;

    @NonNull
    @ManyToOne
    private SeriesEntity seriesEntity;

    @ManyToMany
    private List<TagEntity> tagEntityList;

}
