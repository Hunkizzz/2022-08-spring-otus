package ru.otus.spring.docker.springdocker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.docker.springdocker.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
