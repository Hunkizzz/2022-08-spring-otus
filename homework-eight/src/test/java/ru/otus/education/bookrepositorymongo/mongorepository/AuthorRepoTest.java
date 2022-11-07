package ru.otus.education.bookrepositorymongo.mongorepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Author;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.AuthorRepo;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@DisplayName("Repository для работы с авторами")
class AuthorRepoTest {

    private static final long AUTHOR_ID = 1;
    private static final long AUTHOR2_ID = 2;
    private static final long AUTHOR3_ID = 3;
    private static final int ACTUAL_AUTHOR_BOOK_COUNT_AGGREGATE_LIST_SIZE = 3;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    MongoOperations mongoOperations;

    @Test
    @DisplayName("корректно выводить число книг у авторов")
    void shouldHaveCorrectAuthorsBookCount() {
        List<Author> actual = authorRepo.findAll();
        assertThat(actual).hasSize(ACTUAL_AUTHOR_BOOK_COUNT_AGGREGATE_LIST_SIZE);
        List<Author> expected = new ArrayList();
        expected.add(mongoOperations.findById(AUTHOR_ID, Author.class));
        expected.add(mongoOperations.findById(AUTHOR2_ID, Author.class));
        expected.add(mongoOperations.findById(AUTHOR3_ID, Author.class));
        assertEquals(actual, expected);
    }

    private Query searchAuthor(long id) {
        return new Query(Criteria.where("Id").is(id));
    }


}
