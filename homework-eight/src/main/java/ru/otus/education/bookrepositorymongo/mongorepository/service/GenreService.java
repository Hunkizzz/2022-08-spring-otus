package ru.otus.education.bookrepositorymongo.mongorepository.service;


import ru.otus.education.bookrepositorymongo.mongorepository.domain.Genre;

public interface GenreService {
    Genre getGenre(String genreName);
}
