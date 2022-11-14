package ru.otus.education.web.demo.service;


import ru.otus.education.web.demo.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findByName(String genreName);

    List<Genre> findAll();
}
