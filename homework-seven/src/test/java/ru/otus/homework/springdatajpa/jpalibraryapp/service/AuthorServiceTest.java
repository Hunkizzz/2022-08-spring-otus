package ru.otus.homework.springdatajpa.jpalibraryapp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.springdatajpa.dao.AuthorDao;
import ru.otus.homework.springdatajpa.model.Author;
import ru.otus.homework.springdatajpa.service.AuthorServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorServiceTest {
    @Mock
    AuthorDao authorDao;

    @InjectMocks
    AuthorServiceImpl authorService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(Optional.of(Author.builder().id(1L)
                .name("тест")
                .surname("тест")
                .build())).when(authorDao).findByNameAndSurname(anyString(), anyString());
        doReturn(Optional.of(Author.builder().id(1L)
                .name("тест")
                .surname("тест")
                .build())).when(authorDao).findById(anyLong());
    }

    @DisplayName("должен возвращать корректную запись")
    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Author author = authorService.findByNameAndSurname("тест", "тест");
        assertThat(author.getId()).isEqualTo(1L);
    }
}