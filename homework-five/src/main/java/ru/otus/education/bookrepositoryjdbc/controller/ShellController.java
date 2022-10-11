package ru.otus.education.bookrepositoryjdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.education.bookrepositoryjdbc.model.Book;
import ru.otus.education.bookrepositoryjdbc.service.BookService;
import ru.otus.education.bookrepositoryjdbc.service.IOService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {
    private final BookService bookService;
    private final IOService ioService;

    @ShellMethod(key = {"book list", "list"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.getAll();
        allBooks.forEach(book -> ioService.write(book.toString()));
    }

    @ShellMethod(key = {"add"}, value = "add book to library")
    public void addBook() {
        Book book = bookService.getNewBook();
        bookService.insert(book);
    }

    @ShellMethod(key = {"getById", "gbi"}, value = "get book by Id")
    public void getBookById() {
        long id = ioService.readInt();
        ioService.write(bookService.getById(id).toString());
    }

    @ShellMethod(key = {"deleteById", "dbi"}, value = "delete book by Id")
    public void deleteBookById() {
        long id = ioService.readInt();
        bookService.deleteById(id);
    }

    @ShellMethod(key = "count", value = "count of all books")
    public void bookCount() {
        ioService.write(bookService.getCount());
    }


}
