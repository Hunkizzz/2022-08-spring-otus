package ru.otus.homework.springdatajpa.service;


import ru.otus.homework.springdatajpa.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);

    void addNewBook();

    long getCount();

    List<Book> findAllBooksByAuthorId(long id);


    Map<Book, Long> findAllBooksWithCommentsCount();
}
