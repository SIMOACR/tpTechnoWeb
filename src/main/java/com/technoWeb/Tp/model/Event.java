package com.technoWeb.Tp.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Event {
    long id;

    @NonNull
    private LocalDateTime eventDate;

    @NonNull
    private float value;

    private String comment;

    @NonNull
    private Series series;

    private List<Tag> tagList;
}
