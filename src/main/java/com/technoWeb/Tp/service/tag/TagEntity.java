package com.technoWeb.Tp.service.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    @Column(unique=true)
    private String name;



    public TagEntity(String name) {
        Assert.hasText(name, "name cannot be null, empty or blank");
        Assert.isTrue(Pattern.matches("[a-zA-Z][a-zA-Z0-9_-]*", name), "name must start with a letter and contain only letters, digits, - or _");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagEntity other = (TagEntity) obj;
        return Objects.equals(name, other.name);
    }




}
