package ru.otus.homework.actuator.springactuator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.actuator.springactuator.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
