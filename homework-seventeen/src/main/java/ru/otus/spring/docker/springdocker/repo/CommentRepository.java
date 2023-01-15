package ru.otus.spring.docker.springdocker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.docker.springdocker.domain.Book;
import ru.otus.spring.docker.springdocker.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);
}
