package ru.otus.education.bookrepositoryjdbc.dao;


import ru.otus.education.bookrepositoryjdbc.model.Author;

public interface AuthorDao {
    void insert(Author author);

    Author getById(long id);

    boolean checkByNameAndSurname(String name, String surname);

    Author getByNameAndSurname(String name, String surname);
}
