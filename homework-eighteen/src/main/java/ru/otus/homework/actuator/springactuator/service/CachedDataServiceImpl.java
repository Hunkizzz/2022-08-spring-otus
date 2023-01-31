package ru.otus.homework.actuator.springactuator.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.actuator.springactuator.domain.Author;
import ru.otus.homework.actuator.springactuator.domain.Book;
import ru.otus.homework.actuator.springactuator.domain.Comment;
import ru.otus.homework.actuator.springactuator.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
public class CachedDataServiceImpl implements CachedDataService {

    @Override
    public List<Book> getCachedBooks() {
        List<Book> books = new ArrayList<>();
        books.add(cachedBook());
        return books;
    }

    @Override
    public Book getCachedBook() {
        return cachedBook();
    }

    @Override
    public List<Comment> getCachedComments() {
        List<Comment> comments = new ArrayList<>();
        Comment comment = Comment.builder().id(1L).text("cachedComment").build();
        comments.add(comment);
        return comments;
    }


    private Book cachedBook() {
        Author author = new Author(1L, "cachedAuthorName");
        Genre genre = new Genre(1L, "cachedGenreName");
        return new Book(1L, "cachedBookTitle", author, genre);
    }
}
