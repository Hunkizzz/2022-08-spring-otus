package ru.otus.homework.actuator.springactuator.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.actuator.springactuator.domain.Author;
import ru.otus.homework.actuator.springactuator.domain.Book;
import ru.otus.homework.actuator.springactuator.domain.Genre;
import ru.otus.homework.actuator.springactuator.repo.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CachedDataService cachedDataService;

    @CircuitBreaker(name = "BookRepo", fallbackMethod = "getCachedBooks")
    @Override
    public List<Book> findAll() {
        throw new RuntimeException("test circuit breaker");
//        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void addBook(Book book) {
        addOrUpdateBook(book);
    }

    @CircuitBreaker(name = "BookRepo", fallbackMethod = "getCachedBookById")
    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean update(long id, Book book) {
        if (bookRepository.findById(id).orElse(null) != null) {
            addOrUpdateBook(book);
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteBookById(id);
    }

    private void addOrUpdateBook(Book book) {
        Author author = authorService.findByName(book.getAuthor().getName());
        if (author == null) author = new Author(book.getAuthor().getName());
        Genre genre = genreService.findByName(book.getGenre().getName());
        if (genre == null) genre = new Genre(book.getGenre().getName());
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    private List<Book> getCachedBooks(Exception e) {
        return cachedDataService.getCachedBooks();
    }

    private Book getCachedBookById(Exception e, Long id) {
        return cachedDataService.getCachedBook();
    }

}
