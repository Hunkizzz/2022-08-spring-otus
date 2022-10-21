package ru.otus.homework.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springdatajpa.dao.AuthorDao;
import ru.otus.homework.springdatajpa.model.Author;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    @Transactional(readOnly = false)
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author findById(long id) {
        return authorDao.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        Optional<Author> optionalAuthor = authorDao.findByNameAndSurname(name, surname);
        return optionalAuthor.orElse(null);
    }


}
