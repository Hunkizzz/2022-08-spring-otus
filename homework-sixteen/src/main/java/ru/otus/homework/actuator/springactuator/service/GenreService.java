package ru.otus.homework.actuator.springactuator.service;

import ru.otus.homework.actuator.springactuator.domain.Genre;

public interface GenreService {
    Genre findByName(String genreName);
}
