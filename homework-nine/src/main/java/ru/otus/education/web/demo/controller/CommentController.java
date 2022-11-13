package ru.otus.education.web.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.education.web.demo.domain.Book;
import ru.otus.education.web.demo.domain.Comment;
import ru.otus.education.web.demo.service.CommentService;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/delete/{comment}")
    public String deleteComment(@PathVariable Comment comment) {
        long id = comment.getBook().getId();
        commentService.deleteComment(comment);
        return "redirect:/view/" + id;
    }

    @GetMapping(value = "/addcomment", params = "bookId")
    public String addComment(@RequestParam("bookId") Book book,
                             Model model) {
        model.addAttribute("comment", new Comment("", book));
        return "editComment";
    }

    @PostMapping("/addcomment")
    public String addBook(@ModelAttribute Comment comment) {
        commentService.addOrSaveComment(comment);
        return "redirect:/view/" + comment.getBook().getId();
    }

    @GetMapping("/edit/{comment}")
    public String editComment(@PathVariable Comment comment, Model model) {
        model.addAttribute("comment", comment);
        return "editComment";
    }

}
