package ru.otus.education.bookrepositorymongo.mongorepository.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Author;

public interface AuthorRepo extends MongoRepository<Author, Long> {
    Author findByName(String name);
}
