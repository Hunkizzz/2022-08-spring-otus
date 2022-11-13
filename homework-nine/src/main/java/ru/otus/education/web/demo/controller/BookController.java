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
import ru.otus.education.web.demo.service.BookService;
import ru.otus.education.web.demo.service.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;

    @GetMapping("/")
    public String books(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book(new Author(), new Genre()));
        return "edit";
    }

    @PostMapping("/addbook")
    public String addBook(@ModelAttribute Book book) {
        bookService.addOrSaveBook(book);
        return "redirect:/";
    }

    @PostMapping("/delete/{book}")
    public String deleteBook(@PathVariable Book book) {
        bookService.delete(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{book}")
    public String editBook(@PathVariable Book book, Model model) {
        model.addAttribute("book", book);
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
