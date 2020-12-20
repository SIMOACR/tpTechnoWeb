package com.technoWeb.Tp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Series {
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private boolean publicAccess;

    @NonNull
    private User user;
}
