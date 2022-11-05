package ru.otus.homework.springdatajpa.service;


import ru.otus.homework.springdatajpa.model.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    Author findByNameAndSurname(String name, String surname);

}
