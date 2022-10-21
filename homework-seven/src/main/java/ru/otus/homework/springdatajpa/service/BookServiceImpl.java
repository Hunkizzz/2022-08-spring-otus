package ru.otus.homework.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springdatajpa.dao.BookDao;
import ru.otus.homework.springdatajpa.model.Author;
import ru.otus.homework.springdatajpa.model.Book;
import ru.otus.homework.springdatajpa.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl implements BookService {
    private final IOService ioService;
    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    @Transactional
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book findById(long id) {
        Optional<Book> book = bookDao.findById(id);
        return book.orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByName(String name) {
        return bookDao.findBooksByTitle(name);
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(name);
            bookDao.save(book);
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookDao.delete(book);
        }
    }

    @Override
    @Transactional
    public void addNewBook() {
        ioService.write("Введите наименование книги");
        String title = ioService.read();
        ioService.write("Введите жанр");
        String genreName = ioService.read();
        ioService.write("Введите имя автора");
        String authorName = ioService.read();
        ioService.write("Введите фамилию автора");
        String authorSurname = ioService.read();
        Author author = authorService.findByNameAndSurname(authorName, authorSurname);
        if (author == null)
            author = Author.builder()
                    .name(authorName)
                    .surname(authorSurname)
                    .build();
        Genre genre = genreService.findByName(genreName);
        if (genre == null) genre = new Genre(genreName);
        Book book = Book.builder()
                .title(title)
                .genre(genre)
                .author(author)
                .build();
        bookDao.save(book);
    }

    @Override
    public long getCount() {
        return bookDao.count();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookDao.findAllByAuthorId(id);
    }

    @Override
    public Map<Book, Long> findAllBooksWithCommentsCount() {
        List<ImmutablePair<Book, Long>> pairList = bookDao.findAllBooksWithCommentsCount();
        Map<Book, Long> bookMap = new HashMap<>();
        for (ImmutablePair<Book, Long> pair : pairList)
            bookMap.put(pair.left, pair.right);
        return bookMap;
    }

}