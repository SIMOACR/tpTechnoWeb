package com.technoWeb.Tp.service.user;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(unique=true)
    private String userName;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
