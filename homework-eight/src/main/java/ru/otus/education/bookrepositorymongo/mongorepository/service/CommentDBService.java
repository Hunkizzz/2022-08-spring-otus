package ru.otus.education.bookrepositorymongo.mongorepository.service;


import ru.otus.education.bookrepositorymongo.mongorepository.domain.Comment;

public interface CommentDBService {
    void delete(Comment comment);

    void save(Comment comment);
}
