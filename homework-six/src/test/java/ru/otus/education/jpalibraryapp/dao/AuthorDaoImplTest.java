package ru.otus.education.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.otus.education.jpalibraryapp.model.Author;

@DisplayName("Dao для работы с книгами")
@PropertySource(value = "test.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthorDaoImpl.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AuthorDaoImplTest {
    private final TestEntityManager em;
    private final AuthorDaoImpl authorDao;

    @DisplayName("Должен добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author author = Author.builder()
                .name("тест5")
                .surname("тест5")
                .build();
        authorDao.save(author);
        Author actualAuthor = authorDao.findByNameAndSurname("тест5", "тест5");
        Assertions.assertThat(actualAuthor.getName()).isEqualTo(author.getName());
        Assertions.assertThat(actualAuthor.getSurname()).isEqualTo(author.getSurname());
    }

}
