package ru.otus.homework.actuator.springactuator.service;


import ru.otus.homework.actuator.springactuator.domain.Book;
import ru.otus.homework.actuator.springactuator.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAllComments(Book book);

    void addOrSaveComment(Comment comment);

    void deleteById(Long id);
}
