package ru.otus.education.bookrepositoryjdbc.dao;

import org.springframework.stereotype.Repository;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

@Repository
public interface GenreDao {


    void insert(Genre genre);

    Genre getById(long id);

    boolean checkByName(String genreName);

    Genre getByName(String genreName);
}
