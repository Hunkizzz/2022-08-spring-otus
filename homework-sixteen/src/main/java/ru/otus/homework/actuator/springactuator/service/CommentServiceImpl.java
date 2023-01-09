package ru.otus.homework.actuator.springactuator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.actuator.springactuator.domain.Book;
import ru.otus.homework.actuator.springactuator.domain.Comment;
import ru.otus.homework.actuator.springactuator.repo.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAllComments(Book book) {
        return commentRepository.findAllByBook(book);
    }


    @Override
    public void addOrSaveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
