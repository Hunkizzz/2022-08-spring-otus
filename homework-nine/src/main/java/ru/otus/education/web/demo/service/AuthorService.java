package ru.otus.education.web.demo.service;


import ru.otus.education.web.demo.domain.Author;

public interface AuthorService {
    Author findByName(String authorName);
}
