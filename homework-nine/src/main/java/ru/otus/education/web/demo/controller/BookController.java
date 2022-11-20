package ru.otus.education.web.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.education.web.demo.domain.Author;
import ru.otus.education.web.demo.domain.Book;
import ru.otus.education.web.demo.domain.Comment;
import ru.otus.education.web.demo.domain.Genre;
import ru.otus.education.web.demo.service.AuthorService;
import ru.otus.education.web.demo.service.BookService;
import ru.otus.education.web.demo.service.CommentService;
import ru.otus.education.web.demo.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/")
    public String books(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/create/book")
    public String addBook(@ModelAttribute Book book) {
        bookService.addOrSaveBook(book);
        return "redirect:/";
    }

    @GetMapping("/create/book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book(new Author(), new Genre()));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "edit";
    }

    @PostMapping("/delete/{book}")
    public String deleteBook(@PathVariable Book book) {
        bookService.delete(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{book}")
    public String editBook(@PathVariable Book book, Model model) {
        model.addAttribute("book", book);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "edit";
    }

    @GetMapping("/view/{book}")
    public String showBook(@PathVariable Book book, Model model) {
        model.addAttribute("book", book);
        List<Comment> comments = commentService.findAllComments(book);
        model.addAttribute("comments", comments);
        return "view";
    }
}
