package ru.otus.education.spring.security.springsecurity.service;


import ru.otus.education.spring.security.springsecurity.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findByName(String authorName);

    List<Author> findAll();
}
