package ru.otus.education.jpalibraryapp.service;

import ru.otus.education.jpalibraryapp.model.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    Author findByNameAndSurname(String name, String surname);

}
