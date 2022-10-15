package ru.otus.education.jpalibraryapp.dao;


import ru.otus.education.jpalibraryapp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);

    List<Author> findAll();

    Optional<Author> findById(long id);

    Author findByNameAndSurname(String name, String surname);
}
