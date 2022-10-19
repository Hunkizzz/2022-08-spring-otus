package ru.otus.homework.springdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework.springdatajpa.model.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook_Id(long id);

    @Modifying
    @Query("update Comment c set c.text=:text where c.id=:id")
    void updateTextById(@Param("id") Long id, @Param("text") String text);

    List<Comment> findAllByBookAuthorId(long id);

    void removeCommentById(Comment comment);

    List<Comment> findByBookId(long id);
}
