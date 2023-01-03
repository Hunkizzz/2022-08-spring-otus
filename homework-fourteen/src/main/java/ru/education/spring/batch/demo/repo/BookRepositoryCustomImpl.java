package ru.education.spring.batch.demo.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.education.spring.batch.demo.domain.Book;

import java.math.BigInteger;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public Book findBookByCommentId(BigInteger Id) {
        Query query = Query.query(Criteria.where("comments.$id").is(Id));
        return mongoTemplate.findOne(query, Book.class);
    }
}
