package ru.otus.education.bookrepositoryjdbc.dao;


import ru.otus.education.bookrepositoryjdbc.model.Book;

import java.util.List;

public interface BookDao {
    int getCount();

    long insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

}
