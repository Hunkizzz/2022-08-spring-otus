package ru.otus.education.spring.security.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.spring.security.springsecurity.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
