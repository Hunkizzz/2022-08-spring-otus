package ru.otus.education.bookrepositoryjdbc.service;


import ru.otus.education.bookrepositoryjdbc.model.Author;

public interface AuthorService {
    Author getById(long id);

    Author getAuthor(String name, String surname);
}
