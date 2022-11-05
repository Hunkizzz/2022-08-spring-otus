package ru.otus.education.jpalibraryapp.service;

import ru.otus.education.jpalibraryapp.model.Genre;

import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);


    Genre findByName(String name);


}
