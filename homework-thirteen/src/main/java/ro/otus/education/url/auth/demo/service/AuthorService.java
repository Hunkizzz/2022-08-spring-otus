package ro.otus.education.url.auth.demo.service;


import ro.otus.education.url.auth.demo.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findByName(String authorName);

    List<Author> findAll();
}
