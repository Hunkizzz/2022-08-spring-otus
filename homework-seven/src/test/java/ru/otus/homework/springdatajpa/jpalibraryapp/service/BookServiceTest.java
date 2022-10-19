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
import ru.otus.homework.springdatajpa.dao.BookDao;
import ru.otus.homework.springdatajpa.model.Author;
import ru.otus.homework.springdatajpa.model.Book;
import ru.otus.homework.springdatajpa.model.Genre;
import ru.otus.homework.springdatajpa.service.BookServiceImpl;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {
    @Mock
    BookDao bookDao;

    @InjectMocks
    BookServiceImpl bookService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Author author = Author.builder().name("тест")
                .surname("тест")
                .build();
        Genre genre = new Genre(1L, "test");
        Book book = Book.builder()
                .id(1L)
                .title("test")
                .author(author)
                .genre(genre)
                .build();
        doReturn(Optional.of(book)).when(bookDao).findById(anyLong());
    }

    @DisplayName("должен возвращать корректную запись")
    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Book book = bookService.findById(1L);
        assertThat(book.getId()).isEqualTo(1L);
    }
}