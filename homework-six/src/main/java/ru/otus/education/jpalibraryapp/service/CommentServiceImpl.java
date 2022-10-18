package ru.otus.education.jpalibraryapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.education.jpalibraryapp.dao.CommentDao;
import ru.otus.education.jpalibraryapp.model.Book;
import ru.otus.education.jpalibraryapp.model.Comment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        commentDao.updateTextById(id, text);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        comment.ifPresent(commentDao::deleteById);
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Comment> findAllCommentsByAuthorId(long id) {
        return commentDao.findAllCommentsByAuthorId(id);
    }
}
