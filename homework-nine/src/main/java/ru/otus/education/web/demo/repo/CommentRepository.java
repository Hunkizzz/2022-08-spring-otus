package ru.otus.education.web.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.web.demo.domain.Book;
import ru.otus.education.web.demo.domain.Comment;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);

}
