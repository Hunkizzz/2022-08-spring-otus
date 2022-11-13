package ru.otus.education.bookrepositorymongo.mongorepository.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Genre;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.GenreRepo;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepo genreRepo;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Genre getGenre(String genreName) {
        Genre genre = genreRepo.findByName(genreName);
        if (genre == null) {
            return genreRepo.save(new Genre(sequenceGeneratorService.generateSequence(Genre.SEQUENCE_NAME), genreName));
        } else return genre;
    }
}
