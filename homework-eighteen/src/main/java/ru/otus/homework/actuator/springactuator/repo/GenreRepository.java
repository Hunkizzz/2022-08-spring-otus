package ru.otus.homework.actuator.springactuator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.actuator.springactuator.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
