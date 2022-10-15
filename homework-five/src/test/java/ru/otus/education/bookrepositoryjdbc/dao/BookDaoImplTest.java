package ru.otus.education.bookrepositoryjdbc.dao;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.education.bookrepositoryjdbc.model.Author;
import ru.otus.education.bookrepositoryjdbc.model.Book;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

import java.util.ArrayList;
import java.util.List;


@DisplayName("Dao для работы с книгами")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BookDaoImpl.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class BookDaoImplTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;

    private static final String NEW_BOOK_TITLE = "Идиот";
    private static final long NEW_BOOK_ID = 4;
    private static final long GET_BOOK_ID = 1;
    @Autowired
    private BookDaoImpl bookDao;

    @DisplayName("возращать правильное количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookDao.getCount()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE, new Author(1L, "Федор", "Достоевский"), new Genre(1L, "Роман"));
        bookDao.insert(book);
        Book actualBook = bookDao.getById(NEW_BOOK_ID);
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("получать нужную книгу по Id")
    @Test
    void shouldReturnCorrectBookById() {
        Book book = new Book(GET_BOOK_ID, "Преступление и наказание", new Author(1L, "Федор", "Достоевский"), new Genre(1L, "Роман"));
        Book actualBook = bookDao.getById(GET_BOOK_ID);
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("получить все книги")
    @Test
    void shouldReturnCorrectBookList() {
        Book book1 = new Book(1L, "Преступление и наказание", new Author(1L, "Федор", "Достоевский"), new Genre(1L, "Роман"));
        Book book2 = new Book(2L, "Властелин колец", new Author(2L, "Джон", "Толкин"), new Genre(2L, "Фэнтези"));
        Book book3 = new Book(3L, "Конец вечности", new Author(3L, "Айзек", "Азимов"), new Genre(3L, "Фантастика"));
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        List<Book> actualBooks = bookDao.getAll();
        Assertions.assertThat(actualBooks).isEqualTo(books);
    }

}
