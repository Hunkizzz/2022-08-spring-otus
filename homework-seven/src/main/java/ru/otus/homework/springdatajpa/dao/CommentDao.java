package ru.otus.homework.springdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.springdatajpa.model.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(long id);

    List<Comment> findAllByBookAuthorId(long id);

    void removeCommentById(Comment comment);

    List<Comment> findByBookId(long id);
}
