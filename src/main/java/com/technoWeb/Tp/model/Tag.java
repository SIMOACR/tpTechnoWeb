package com.technoWeb.Tp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Tag {
    private long id;

    @NonNull
    private String name;
}
