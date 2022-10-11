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
import ru.otus.education.bookrepositoryjdbc.dao.GenreDaoImpl;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenreServiceTest {
    @Mock
    GenreDaoImpl genreDao;

    @InjectMocks
    GenreServiceImpl genreService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(new Genre(1L, "test")).when(genreDao).getByName(anyString());
        doReturn(true).when(genreDao).checkByName(anyString());
        doReturn(new Genre(1L, "test")).when(genreDao).getById(anyLong());
    }

    @DisplayName("должен возвращать корректную запись")
    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Genre genre = genreService.getGenre("тест");
        assertThat(genre.getId()).isEqualTo(1L);
    }
}