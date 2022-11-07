package ru.otus.education.bookrepositorymongo.mongorepository.service;


import ru.otus.education.bookrepositorymongo.mongorepository.domain.Book;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Comment;

import java.util.List;

public interface BookService {
    void addBook(String title, String authorName, String genreName);

    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);

    long getCount();

    void updateNameById(long id, String name);

    List<Book> findByName(String name);

    void addComment(long bookId, String commentText);

    List<Comment> findCommentsByBookId(long id);

    List<Book> findAllBooksByAuthorId(long id);
}
