package ru.otus.homework.actuator.springactuator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.actuator.springactuator.domain.Author;
import ru.otus.homework.actuator.springactuator.repo.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findByName(String authorName) {
        return authorRepository.findByName(authorName).orElse(null);
    }
}
