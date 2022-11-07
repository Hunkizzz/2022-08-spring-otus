package ru.otus.education.bookrepositorymongo.mongorepository.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Author;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.AuthorRepo;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Author getAuthor(String authorName) {
        Author author = authorRepo.findByName(authorName);
        if (author == null) {
            return authorRepo.save(new Author(sequenceGeneratorService.generateSequence(Author.SEQUENCE_NAME), authorName));
        } else return author;
    }

    @Override
    public Author findById(long id) {
        return authorRepo.findById(id).orElse(null);
    }
}
