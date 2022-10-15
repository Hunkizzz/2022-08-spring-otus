package ru.otus.education.jpalibraryapp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.education.jpalibraryapp.dao.AuthorDaoImpl;
import ru.otus.education.jpalibraryapp.model.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorServiceTest {
    @Mock
    AuthorDaoImpl authorDao;

    @InjectMocks
    AuthorServiceImpl authorService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(new Author(1L, "тест", "тест")).when(authorDao).findByNameAndSurname(anyString(), anyString());
        doReturn(Optional.of(new Author(1L, "тест", "тест"))).when(authorDao).findById(anyLong());
    }

    @DisplayName("должен возвращать корректную запись")
    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Author author = authorService.findByNameAndSurname("тест", "тест");
        assertThat(author.getId()).isEqualTo(1L);
    }
}