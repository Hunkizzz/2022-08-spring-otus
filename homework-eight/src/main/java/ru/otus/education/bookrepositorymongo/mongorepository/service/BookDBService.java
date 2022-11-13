package ru.otus.education.bookrepositorymongo.mongorepository.service;


import ru.otus.education.bookrepositorymongo.mongorepository.domain.Book;

import java.util.List;

public interface BookDBService {
    void save(Book book);

    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);

    long count();

    List<Book> findAllByTitle(String name);

    List<Book> findAllByAuthorId(long id);
}
