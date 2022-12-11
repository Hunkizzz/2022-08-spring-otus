package ru.otus.education.spring.security.springsecurity.service;

import ru.otus.education.spring.security.springsecurity.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    void addOrSaveBook(Book book);

    void delete(Book book);
}
