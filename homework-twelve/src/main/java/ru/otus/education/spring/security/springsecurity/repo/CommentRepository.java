package ru.otus.education.spring.security.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.spring.security.springsecurity.domain.Book;
import ru.otus.education.spring.security.springsecurity.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);

}
