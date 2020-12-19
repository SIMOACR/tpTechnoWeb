package com.technoWeb.Tp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    private long id;

    @NonNull
    private String userName;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
