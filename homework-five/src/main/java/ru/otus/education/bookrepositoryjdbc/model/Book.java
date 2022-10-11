package ru.otus.education.bookrepositoryjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private Author author;
    private Genre genre;

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title, Genre genre, Author author) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return id +
                " Наименование: " + title +
                ". Имя автора: " + author.getName() +
                ". Фамилия автора: " + author.getSurname() +
                ". Жанр: " + genre.getName();
    }
}
