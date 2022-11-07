package ru.otus.education.bookrepositorymongo.mongorepository.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Book;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.BookRepo;

import java.util.List;

@Service
@Qualifier("bookDBService")
@RequiredArgsConstructor
public class BookDBServiceImpl implements BookDBService {
    private final SequenceGeneratorService sequenceGeneratorService;
    private final BookRepo bookRepo;

    @Override
    public void save(Book book) {
        book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        bookRepo.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(long id) {
        return bookRepo.findById(id);
    }

    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public long count() {
        return bookRepo.count();
    }

    @Override
    public List<Book> findAllByTitle(String name) {
        return bookRepo.findAllByTitle(name);
    }

    @Override
    public List<Book> findAllByAuthorId(long id) {
        return bookRepo.findAllByAuthorId(id);
    }
}
