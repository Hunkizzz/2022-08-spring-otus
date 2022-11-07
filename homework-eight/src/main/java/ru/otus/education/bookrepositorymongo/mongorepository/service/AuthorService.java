package ru.otus.education.bookrepositorymongo.mongorepository.service;

import ru.otus.education.bookrepositorymongo.mongorepository.domain.Author;

public interface AuthorService {
    Author getAuthor(String authorName);

    Author findById(long id);
}
