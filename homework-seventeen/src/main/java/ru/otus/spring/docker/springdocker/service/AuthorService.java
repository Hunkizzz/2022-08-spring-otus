package ru.otus.spring.docker.springdocker.service;


import ru.otus.spring.docker.springdocker.domain.Author;

public interface AuthorService {
    Author findByName(String authorName);
}
