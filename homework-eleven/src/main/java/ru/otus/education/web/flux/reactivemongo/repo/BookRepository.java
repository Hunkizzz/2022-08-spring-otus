package ru.otus.education.web.flux.reactivemongo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.education.web.flux.reactivemongo.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, Long> {
}

