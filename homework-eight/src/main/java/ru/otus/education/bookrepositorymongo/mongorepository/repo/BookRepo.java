package ru.otus.education.bookrepositorymongo.mongorepository.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Book;

import java.util.List;

public interface BookRepo extends MongoRepository<Book, Long> {

    Book findById(long id);

    List<Book> findAllByTitle(String title);

    List<Book> findAllByAuthorId(long id);
}
