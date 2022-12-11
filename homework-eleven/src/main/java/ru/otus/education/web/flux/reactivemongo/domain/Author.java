package ru.otus.education.web.flux.reactivemongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private String name;


    @Override
    public String toString() {
        return "Author{" +
                ", name='" + name + '\'' +
                '}';
    }


}
