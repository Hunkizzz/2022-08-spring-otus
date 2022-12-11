package ro.otus.education.url.auth.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.otus.education.url.auth.demo.domain.Author;
import ro.otus.education.url.auth.demo.domain.Book;
import ro.otus.education.url.auth.demo.domain.Comment;
import ro.otus.education.url.auth.demo.domain.Genre;
import ro.otus.education.url.auth.demo.service.AuthorService;
import ro.otus.education.url.auth.demo.service.BookService;
import ro.otus.education.url.auth.demo.service.CommentService;
import ro.otus.education.url.auth.demo.service.GenreService;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create/book")
    public String addBook(@ModelAttribute Book book) {
        bookService.addOrSaveBook(book);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create/book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book(new Author(), new Genre()));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete/{book}")
    public String deleteBook(@PathVariable Book book) {
        bookService.delete(book);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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
