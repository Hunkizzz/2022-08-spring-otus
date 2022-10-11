package ru.otus.education.bookrepositoryjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {
    private Long id;
    private String name;

    public Genre(String genreName) {
        this.name = genreName;
    }
}
