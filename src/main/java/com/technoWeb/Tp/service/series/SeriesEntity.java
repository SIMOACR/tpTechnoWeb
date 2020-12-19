package com.technoWeb.Tp.service.series;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "series")
public class SeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(unique=true)
    private String title;

    @NonNull
    private String description;

    @NonNull
    private boolean publicAccess;

}
