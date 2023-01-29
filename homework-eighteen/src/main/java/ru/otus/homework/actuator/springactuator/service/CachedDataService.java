package ru.otus.homework.actuator.springactuator.service;

import ru.otus.homework.actuator.springactuator.domain.Book;
import ru.otus.homework.actuator.springactuator.domain.Comment;

import java.util.List;

public interface CachedDataService {
    List<Book> getCachedBooks();

    Book getCachedBook();

    List<Comment> getCachedComments();
}
