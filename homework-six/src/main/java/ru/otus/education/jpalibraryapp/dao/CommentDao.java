package ru.otus.education.jpalibraryapp.dao;

import ru.otus.education.jpalibraryapp.model.Comment;

import java.util.List;

public interface CommentDao {
    Comment save(Comment comment);

    List<Comment> findByBookId(long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    List<Comment> findAllCommentsByAuthorId(long id);
}