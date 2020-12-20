package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.service.user.UserEntity;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Pattern;

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


    public SeriesEntity(String title) {
        Assert.hasText(title, "title cannot be null, empty or blank");
        Assert.isTrue(Pattern.matches("[a-zA-Z][a-zA-Z0-9_-]*", title), "title must start with a letter and contain only letters, digits, - or _");

        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SeriesEntity other = (SeriesEntity) obj;
        return Objects.equals(title, other.title);
    }

    @NonNull
    private String description;

    public String getDescription(){
        return description;
    }

    @NonNull
    private boolean publicAccess;

    @NonNull
    @ManyToOne
    private UserEntity userEntity;


}
