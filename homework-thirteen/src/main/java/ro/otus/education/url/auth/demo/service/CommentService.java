package ro.otus.education.url.auth.demo.service;

import ro.otus.education.url.auth.demo.domain.Book;
import ro.otus.education.url.auth.demo.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAllComments(Book book);

    void deleteComment(Comment comment);

    void addOrSaveComment(Comment comment);
}
