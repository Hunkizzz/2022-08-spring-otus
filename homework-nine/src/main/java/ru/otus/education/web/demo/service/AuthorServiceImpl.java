package ru.otus.education.web.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.web.demo.domain.Author;
import ru.otus.education.web.demo.repo.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findByName(String authorName) {
        return authorRepository.findByName(authorName).orElse(null);
    }
}
