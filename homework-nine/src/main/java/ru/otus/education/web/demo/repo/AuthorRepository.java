package ru.otus.education.web.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.web.demo.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
