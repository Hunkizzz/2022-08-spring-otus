package ru.otus.education.web.flux.reactivemongo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Genre {

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
