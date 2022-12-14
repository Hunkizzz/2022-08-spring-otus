package ru.otus.homework.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springdatajpa.dao.CommentDao;
import ru.otus.homework.springdatajpa.model.Book;
import ru.otus.homework.springdatajpa.model.Comment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final IOService ioService;
    private final BookService bookService;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> findByBookId(long id) {
        return commentDao.findByBookId(id);
    }

    @Override
    @Transactional
    public void updateTextById(long id, String text) {
        Optional<Comment> commentOptional = commentDao.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(text);
            commentDao.save(comment);
        }

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        comment.ifPresent(commentDao::removeCommentById);
    }

    @Override
    @Transactional
    public void addNewComment() {
        ioService.write("Введите id книги для добавления комментария");
        int bookId = ioService.readInt();
        Book book = bookService.findById(bookId);
        if (book != null) {
            ioService.write("Введите комментарий - " + book.getTitle());
            String commentText = ioService.read();
            Comment comment = new Comment(commentText, book);
            commentDao.save(comment);
        } else {
            ioService.write("Книга не найдена");
        }
    }

    @Override
    public List<Comment> findAllCommentsByAuthorId(long id) {
        return commentDao.findAllByBookAuthorId(id);
    }
}
