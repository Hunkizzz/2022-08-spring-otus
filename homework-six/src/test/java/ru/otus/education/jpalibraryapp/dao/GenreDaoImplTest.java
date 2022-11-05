package ru.otus.education.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.otus.education.jpalibraryapp.model.Genre;

import java.util.Optional;

@DisplayName("Dao для работы с книгами")
@PropertySource(value = "test.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(GenreDaoImpl.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GenreDaoImplTest {
    private static final long NEW_GENRE_ID = 4;
    private final GenreDaoImpl genreDao;

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
