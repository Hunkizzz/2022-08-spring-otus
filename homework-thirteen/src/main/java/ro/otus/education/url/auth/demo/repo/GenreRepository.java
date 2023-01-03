package ro.otus.education.url.auth.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.otus.education.url.auth.demo.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
