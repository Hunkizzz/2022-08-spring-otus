package ru.otus.education.bookrepositoryjdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositoryjdbc.dao.GenreDao;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getGenre(String genreName) {
        if (!checkGenreInBase(genreName)) genreDao.insert(new Genre(genreName));
        return genreDao.getByName(genreName);
    }

    private boolean checkGenreInBase(String genreName) {
        return genreDao.checkByName(genreName);
    }
}
