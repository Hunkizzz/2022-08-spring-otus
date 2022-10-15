package ru.otus.education.jpalibraryapp.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.education.jpalibraryapp.dao.BookDao;
import ru.otus.education.jpalibraryapp.model.Author;
import ru.otus.education.jpalibraryapp.model.Book;
import ru.otus.education.jpalibraryapp.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Book findById(long id) {
        Optional<Book> book = bookDao.findById(id);
        return book.orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findByName(String name) {
        return bookDao.findByName(name);
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        bookDao.updateNameById(id, name);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookDao.deleteById(id);
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
        ioService.write("Введите имя автора");
        String authorSurname = ioService.read();
        Author author = authorService.findByNameAndSurname(authorName, authorSurname);
        if (author == null) author = new Author(authorName, authorSurname);
        Genre genre = genreService.findByName(genreName);
        if (genre == null) genre = new Genre(genreName);
        Book book = new Book(title, genre, author);
        bookDao.save(book);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getCount() {
        return bookDao.getCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookDao.findAllBooksByAuthorId(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAllWithComments() {
        return bookDao.findAllWithComments();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<Book, Long> findAllBooksWithCommentsCount() {
        List<ImmutablePair<Book, Long>> pairList = bookDao.findAllBooksWithCommentsCount();
        Map<Book, Long> bookMap = new HashMap<>();
        for (ImmutablePair<Book, Long> pair : pairList)
            bookMap.put(pair.left, pair.right);
        return bookMap;
    }

}