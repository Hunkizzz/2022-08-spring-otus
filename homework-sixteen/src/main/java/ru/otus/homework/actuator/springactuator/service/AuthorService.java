package ru.otus.homework.actuator.springactuator.service;

import ru.otus.homework.actuator.springactuator.domain.Author;

public interface AuthorService {
    Author findByName(String authorName);
}
