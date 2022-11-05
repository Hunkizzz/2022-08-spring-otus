package ru.otus.homework.springdatajpa.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.springdatajpa.dao.GenreDao;
import ru.otus.homework.springdatajpa.model.Genre;

import java.util.Optional;

@DisplayName("Dao для работы с книгами")
@PropertySource(value = "test.yaml")
@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GenreDaoImplTest {
    private static final long NEW_GENRE_ID = 4;
    private final GenreDao genreDao;

    @DisplayName("Должен добавлять жанр в БД")
    @Test
    void shouldInsertBook() {
        Genre genre = new Genre("тест");
        genreDao.save(genre);
        Optional<Genre> actualGenre = genreDao.findById(NEW_GENRE_ID);
        assert actualGenre.orElse(null) != null;
        Assertions.assertThat(actualGenre.orElse(null).getName()).isEqualTo(genre.getName());
    }
}
