package ru.otus.homework.springdatajpa.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.springdatajpa.dao.CommentDao;
import ru.otus.homework.springdatajpa.model.Book;
import ru.otus.homework.springdatajpa.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с комментариями")
@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CommentRepositoryTest {

    private static final String NEW_COMMENT_TEXT = "new comment text";
    private static final long FIRST_BOOK_ID = 1;
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 2;
    private static final long FIRST_COMMENT_ID = 1;

    private final CommentDao commentRepository;
    private final TestEntityManager em;

    @DisplayName("должен корректно сохранять комментарий в бд")
    @Test
    void shouldSaveComment() {
        Book book = em.find(Book.class, FIRST_BOOK_ID);
        var comment = new Comment(NEW_COMMENT_TEXT, book);
        comment = commentRepository.save(comment);

        assertThat(comment.getId()).isPositive();

        val actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isNotNull().matches(b -> !b.getText().equals(""))
                .matches(b -> b.getBook() != null);
    }

    @DisplayName("должен загружать все комментарии к книге по её Id")
    @Test
    void shouldFindAllCommentsByBookId() {
        val comments = commentRepository.findAllByBookId(FIRST_BOOK_ID);
        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
                .allMatch(comment -> !comment.getText().equals(""))
                .allMatch(comment -> comment.getBook().getTitle() != null);
    }

    @DisplayName(" должен удалять комментарий по его Id")
    @Test
    void shouldDeleteBookNameById() {
        commentRepository.deleteById(FIRST_COMMENT_ID);
        val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(deletedComment).isNull();
    }
}
