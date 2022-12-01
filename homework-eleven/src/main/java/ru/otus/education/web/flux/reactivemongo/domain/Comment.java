package ru.otus.education.web.flux.reactivemongo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
    private String text;

    public Comment(String text) {
        this.text = text;
    }
}
