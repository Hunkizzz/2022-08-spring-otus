package ru.otus.spring.docker.springdocker.service;


import ru.otus.spring.docker.springdocker.domain.Genre;

public interface GenreService {
    Genre findByName(String genreName);
}
