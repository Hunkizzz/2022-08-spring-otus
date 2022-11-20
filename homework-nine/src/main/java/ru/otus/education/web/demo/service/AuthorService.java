package ru.otus.education.web.demo.service;


import ru.otus.education.web.demo.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findByName(String authorName);

    List<Author> findAll();
}
