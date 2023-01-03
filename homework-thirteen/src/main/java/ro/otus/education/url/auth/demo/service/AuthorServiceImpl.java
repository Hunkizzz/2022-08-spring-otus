package ro.otus.education.url.auth.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.otus.education.url.auth.demo.domain.Author;
import ro.otus.education.url.auth.demo.repo.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findByName(String authorName) {
        return authorRepository.findByName(authorName).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
