package ru.otus.education.web.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.web.demo.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
