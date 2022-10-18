package ru.otus.education.jpalibraryapp.dao;

import lombok.AllArgsConstructor;
import lombok.val;
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
import ru.otus.education.jpalibraryapp.model.Book;
import ru.otus.education.jpalibraryapp.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Dao для работы с книгами")
@PropertySource(value = "test.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({BookDaoImpl.class, CommentDaoImpl.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
class BookDaoImplTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;

    private static final String NEW_BOOK_TITLE = "Идиот";
    private static final long NEW_BOOK_ID = 4;
    private static final long GET_BOOK_ID = 1;
    private static final long FIRST_BOOK_ID = 1;
    private static final String FIRST_BOOK_NAME = "Преступление и наказание";

    private final TestEntityManager em;
    private final BookDaoImpl bookDao;
    private final CommentDaoImpl commentDao;

    @DisplayName("возращать правильное количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookDao.getCount()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = Book.builder().id(NEW_BOOK_ID)
                .title(NEW_BOOK_TITLE)
                .author(Author.builder().id(1L)
                        .name("Федор")
                        .surname("Достоевский")
                        .build())
                .genre(new Genre(1L, "Роман"))
                .build();
        bookDao.save(book);
        Optional<Book> actualBook = bookDao.findById(NEW_BOOK_ID);
        Assertions.assertThat(actualBook.orElse(null)).isEqualTo(book);
    }

    @DisplayName("должен корректно сохранять книгу в бд")
    @Test
    void shouldSaveBook() {
        val author = Author.builder().id(1L)
                .name("Федор")
                .surname("Достоевский")
                .build();
        val genre = new Genre(1L, "Роман");
        var book = Book.builder()
                .title(NEW_BOOK_TITLE)
                .genre(genre)
                .author(author)
                .build();
        book = bookDao.save(book);

        assertThat(book.getId()).isPositive();

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.getTitle().equals(""))
                .matches(b -> b.getAuthor() != null)
                .matches(b -> b.getGenre() != null);
    }

    @DisplayName("должен загружать информацию о нужной книге по её Id")
    @Test
    void shouldFindBookById() {
        val optionalActualBook = bookDao.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг с информацией об авторе и жанре")
    @Test
    void shouldReturnCorrectBookListWithGenreAndAuthor() {
        val books = bookDao.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_BOOKS_COUNT)
                .allMatch(book -> !book.getTitle().equals(""))
                .allMatch(book -> book.getGenre() != null)
                .allMatch(book -> book.getAuthor() != null);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее имени")
    @Test
    void shouldFindBookByName() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = bookDao.findByName(FIRST_BOOK_NAME);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName(" должен изменять наименование нужной книге по ее Id")
    @Test
    void shouldUpdateBookNameById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldName = firstBook.getTitle();
        em.clear();
        Book book = bookDao.findById(FIRST_BOOK_ID).get();
        book.setTitle(NEW_BOOK_TITLE);
        bookDao.save(book);
        val updatedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(updatedBook.getTitle()).isNotEqualTo(oldName).isEqualTo(NEW_BOOK_TITLE);
    }

    @DisplayName(" должен удалять нужную книгу по ее Id")
    @Test
    void shouldDeleteBookNameById() {
        em.clear();
        Book book = bookDao.findById(FIRST_BOOK_ID).get();
        bookDao.deleteById(book);
        val deletedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(deletedBook).isNull();
    }
}
