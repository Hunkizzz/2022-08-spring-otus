package ru.otus.education.spring.security.springsecurity.service;


import ru.otus.education.spring.security.springsecurity.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findByName(String genreName);

    List<Genre> findAll();
}
