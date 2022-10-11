package ru.otus.education.bookrepositoryjdbc.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.education.bookrepositoryjdbc.dao.AuthorDaoImpl;
import ru.otus.education.bookrepositoryjdbc.model.Author;

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
        doReturn(new Author(1L, "тест", "тест")).when(authorDao).getByNameAndSurname(anyString(), anyString());
        doReturn(true).when(authorDao).checkByNameAndSurname(anyString(), anyString());
        doReturn(new Author(1L, "тест", "тест")).when(authorDao).getById(anyLong());
    }

    @DisplayName("должен возвращать корректную запись")
    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Author author = authorService.getAuthor("тест", "тест");
        assertThat(author.getId()).isEqualTo(1L);
    }
}