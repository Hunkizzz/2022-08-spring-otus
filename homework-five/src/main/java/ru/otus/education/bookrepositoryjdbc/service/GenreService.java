package ru.otus.education.bookrepositoryjdbc.service;


import ru.otus.education.bookrepositoryjdbc.model.Genre;

public interface GenreService {
    Genre getById(long id);

    Genre getGenre(String genreName);
}
