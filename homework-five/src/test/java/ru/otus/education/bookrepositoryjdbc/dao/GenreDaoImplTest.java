package ru.otus.education.bookrepositoryjdbc.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

@DisplayName("Dao для работы с книгами")
@JdbcTest
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
        genreDao.insert(genre);
        Genre actualGenre = genreDao.getById(NEW_GENRE_ID);
        Assertions.assertThat(actualGenre.getName()).isEqualTo(genre.getName());
    }
}
