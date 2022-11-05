package ru.otus.homework.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springdatajpa.dao.GenreDao;
import ru.otus.homework.springdatajpa.model.Genre;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    public Optional<Genre> findById(long id) {
        return genreDao.findById(id);
    }


    @Override
    public Genre findByName(String name) {
        return genreDao.findByName(name);
    }
}


