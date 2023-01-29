package ru.otus.spring.docker.springdocker.service;


import ru.otus.spring.docker.springdocker.domain.Book;
import ru.otus.spring.docker.springdocker.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAllComments(Book book);

    void addOrSaveComment(Comment comment);

    void deleteById(Long id);
}
