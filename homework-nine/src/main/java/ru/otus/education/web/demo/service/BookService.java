package ru.otus.education.web.demo.service;


import ru.otus.education.web.demo.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    void addOrSaveBook(Book book);

    void delete(Book book);
}
