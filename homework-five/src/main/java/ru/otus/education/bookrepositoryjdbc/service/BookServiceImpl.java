package ru.otus.education.bookrepositoryjdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositoryjdbc.dao.BookDao;
import ru.otus.education.bookrepositoryjdbc.model.Author;
import ru.otus.education.bookrepositoryjdbc.model.Book;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final IOService ioService;
    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public int getCount() {
        return bookDao.getCount();
    }

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book getNewBook() {
        ioService.write("Введите наименование книги");
        String title = ioService.read();
        ioService.write("Введите жанр");
        String genreName = ioService.read();
        ioService.write("Введите имя автора");
        String authorName = ioService.read();
        ioService.write("Введите фамилию автора");
        String authorSurname = ioService.read();
        Genre genre = genreService.getGenre(genreName);
        Author author = authorService.getAuthor(authorName, authorSurname);
        return new Book(title, genre, author);
    }
}