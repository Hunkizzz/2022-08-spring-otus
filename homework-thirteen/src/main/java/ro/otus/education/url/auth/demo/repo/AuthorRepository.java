package ro.otus.education.url.auth.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.otus.education.url.auth.demo.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
