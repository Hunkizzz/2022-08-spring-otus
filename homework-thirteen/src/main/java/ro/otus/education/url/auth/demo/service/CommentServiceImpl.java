package ro.otus.education.url.auth.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.otus.education.url.auth.demo.domain.Book;
import ro.otus.education.url.auth.demo.domain.Comment;
import ro.otus.education.url.auth.demo.repo.CommentRepository;

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
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void addOrSaveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
