package ru.otus.education.bookrepositoryjdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositoryjdbc.dao.AuthorDao;
import ru.otus.education.bookrepositoryjdbc.model.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author getAuthor(String name, String surname) {
        if (!checkAuthorInBase(name, surname))
            return null;
        else
            return authorDao.getByNameAndSurname(name, surname);
    }

    private boolean checkAuthorInBase(String name, String surname) {
        return authorDao.checkByNameAndSurname(name, surname);
    }
}
