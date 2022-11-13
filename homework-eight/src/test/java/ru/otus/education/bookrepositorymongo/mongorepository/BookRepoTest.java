package ru.otus.education.bookrepositorymongo.mongorepository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Book;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.BookRepo;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.CommentRepo;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan({"ru.otus.education.bookrepositorymongo.mongorepository.repo"})
@DisplayName("Repository для работы с книгами")
class BookRepoTest {
    private static final int CORRECT_COMMENTS_COUNT_AFTER_DELETE = 1;
    private static final long COMMENT_ID = 1;
    private static final long BOOK_ID = 1;
    private static final int CORRECT_BOOKS_COUNT = 5;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName("корректное количество книг")
    void shouldReturnCorrectBookList() {
        val books = bookRepo.findAll();
        assertThat(books).isNotNull().hasSize(CORRECT_BOOKS_COUNT);
    }

    @Test
    @DisplayName("корректно удалять комментарий из книги")
    void shouldCorrectDeleteCommentById() {
        commentRepo.deleteById(COMMENT_ID);
        assertThat(Objects.requireNonNull(mongoOperations.findOne(searchBook(BOOK_ID), Book.class)).getComments()).hasSize(CORRECT_COMMENTS_COUNT_AFTER_DELETE);
    }
    private Query searchBook(long id) {
        return new Query(Criteria.where("Id").is(id));
    }

}
