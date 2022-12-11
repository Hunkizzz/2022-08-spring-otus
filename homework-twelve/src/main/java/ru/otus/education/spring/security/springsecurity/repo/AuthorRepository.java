package ru.otus.education.spring.security.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.spring.security.springsecurity.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
