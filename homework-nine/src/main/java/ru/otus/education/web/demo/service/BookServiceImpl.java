package ru.otus.education.web.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.education.web.demo.domain.Author;
import ru.otus.education.web.demo.domain.Book;
import ru.otus.education.web.demo.domain.Genre;
import ru.otus.education.web.demo.repo.BookRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void addOrSaveBook(Book book) {
        Author author = authorService.findByName(book.getAuthor().getName());
        if (author == null) author = new Author(book.getAuthor().getName());
        Genre genre = genreService.findByName(book.getGenre().getName());
        if (genre == null) genre = new Genre(book.getGenre().getName());
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }


}
