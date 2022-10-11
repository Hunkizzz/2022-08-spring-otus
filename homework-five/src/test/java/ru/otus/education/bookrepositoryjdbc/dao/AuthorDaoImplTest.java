package ru.otus.education.bookrepositoryjdbc.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import ru.otus.education.bookrepositoryjdbc.model.Author;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthorDaoImpl.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AuthorDaoImplTest {
    private static final long NEW_AUTHOR_ID = 4;
    private final AuthorDaoImpl authorDao;

    @DisplayName("Должен добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author author = new Author("тест5", "тест5");
        authorDao.insert(author);
        Author actualAuthor = authorDao.getByNameAndSurname("тест5", "тест5");
        Assertions.assertThat(actualAuthor.getName()).isEqualTo(author.getName());
        Assertions.assertThat(actualAuthor.getSurname()).isEqualTo(author.getSurname());
    }

    @DisplayName("Должен выдавать исключение при добавление автора с таким же именем и фамилией")
    @Test
    void shouldThrowExceptionDuplicateAuthor() {
        Author author = new Author("тест2", "тест2");
        authorDao.insert(author);
        assertThatThrownBy(() -> {
            authorDao.insert(author);
        }).isInstanceOf(DuplicateKeyException.class)
                .hasMessageContaining("Unique index or primary key violation");
        author.setName("тест3");
        authorDao.insert(author);
    }
}
