package ro.otus.education.url.auth.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.otus.education.url.auth.demo.domain.Book;
import ro.otus.education.url.auth.demo.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);

}
