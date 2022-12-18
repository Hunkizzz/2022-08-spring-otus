package ru.education.spring.batch.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.education.spring.batch.demo.domain.Book;

import java.math.BigInteger;


public interface BookRepository extends MongoRepository<Book, BigInteger>, BookRepositoryCustom {
}
