package ru.otus.homework.springdatajpa.service;


import ru.otus.homework.springdatajpa.model.Genre;

import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);


    Genre findByName(String name);


}
