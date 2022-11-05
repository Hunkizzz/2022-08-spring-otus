package ru.otus.education.jpalibraryapp.dao;

import org.springframework.stereotype.Repository;
import ru.otus.education.jpalibraryapp.model.Genre;

import java.util.Optional;

@Repository
public interface GenreDao {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    Genre findByName(String name);
}
