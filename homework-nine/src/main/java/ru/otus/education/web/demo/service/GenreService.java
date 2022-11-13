package ru.otus.education.web.demo.service;


import ru.otus.education.web.demo.domain.Genre;

public interface GenreService {
    Genre findByName(String genreName);
}
