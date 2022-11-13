package ru.otus.education.bookrepositorymongo.mongorepository.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Comment;

public interface CommentRepo extends MongoRepository<Comment, Long> {
}
