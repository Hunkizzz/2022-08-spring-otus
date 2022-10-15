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
import ru.otus.education.jpalibraryapp.dao.BookDaoImpl;
import ru.otus.education.jpalibraryapp.model.Author;
import ru.otus.education.jpalibraryapp.model.Book;
import ru.otus.education.jpalibraryapp.model.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {
    @Mock
    BookDaoImpl bookDao;

    @InjectMocks
    BookServiceImpl bookService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Author author = new Author("тест", "тест");
        Genre genre = new Genre(1L, "test");
        Book book = new Book(1L, "test", author, genre);
        doReturn(Optional.of(book)).when(bookDao).findById(anyLong());
    }

    @DisplayName("должен возвращать корректную запись")
    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Book book = bookService.findById(1L);
        assertThat(book.getId()).isEqualTo(1L);
    }
}