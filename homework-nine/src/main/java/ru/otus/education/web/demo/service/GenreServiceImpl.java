package ru.otus.education.web.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.web.demo.domain.Genre;
import ru.otus.education.web.demo.repo.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Genre findByName(String genreName) {
        return genreRepository.findByName(genreName);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }


}
