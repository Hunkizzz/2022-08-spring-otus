package ru.otus.education.web.demo.service;


import ru.otus.education.web.demo.domain.Book;
import ru.otus.education.web.demo.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAllComments(Book book);

    void deleteComment(Comment comment);

    void addOrSaveComment(Comment comment);
}
