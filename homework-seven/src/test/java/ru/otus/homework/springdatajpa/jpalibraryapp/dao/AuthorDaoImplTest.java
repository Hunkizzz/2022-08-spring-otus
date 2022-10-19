package ru.otus.homework.springdatajpa.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.springdatajpa.dao.AuthorDao;
import ru.otus.homework.springdatajpa.model.Author;

import java.util.Optional;

@DisplayName("Dao для работы с книгами")
@PropertySource(value = "test.yaml")
@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AuthorDaoImplTest {
    private final AuthorDao authorDao;

    @DisplayName("Должен добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author author = Author.builder()
                .name("тест5")
                .surname("тест5")
                .build();
        authorDao.save(author);
        Optional<Author> actualAuthor = authorDao.findByNameAndSurname("тест5", "тест5");

        Assertions.assertThat(actualAuthor.get().getName()).isEqualTo(author.getName());
        Assertions.assertThat(actualAuthor.get().getSurname()).isEqualTo(author.getSurname());
    }

}
