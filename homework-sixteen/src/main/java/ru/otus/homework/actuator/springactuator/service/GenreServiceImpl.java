package ru.otus.homework.actuator.springactuator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.actuator.springactuator.domain.Genre;
import ru.otus.homework.actuator.springactuator.repo.GenreRepository;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Genre findByName(String genreName) {
        return genreRepository.findByName(genreName);
    }
}
