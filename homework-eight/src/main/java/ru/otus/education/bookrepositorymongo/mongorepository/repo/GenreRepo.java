package ru.otus.education.bookrepositorymongo.mongorepository.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Genre;

public interface GenreRepo extends MongoRepository<Genre, Long> {
    Genre findByName(String name);
}
