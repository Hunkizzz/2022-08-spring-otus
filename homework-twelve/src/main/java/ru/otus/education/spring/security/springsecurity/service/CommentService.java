package ru.otus.education.spring.security.springsecurity.service;


import ru.otus.education.spring.security.springsecurity.domain.Book;
import ru.otus.education.spring.security.springsecurity.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAllComments(Book book);

    void deleteComment(Comment comment);

    void addOrSaveComment(Comment comment);
}
