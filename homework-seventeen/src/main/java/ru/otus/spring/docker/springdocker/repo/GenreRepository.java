package ru.otus.spring.docker.springdocker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.docker.springdocker.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
