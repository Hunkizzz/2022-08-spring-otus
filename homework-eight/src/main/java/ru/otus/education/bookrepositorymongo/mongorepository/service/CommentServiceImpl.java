package ru.otus.education.bookrepositorymongo.mongorepository.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.BookRepo;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.CommentRepo;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final BookRepo bookRepo;

    @Override
    public void deleteById(long id) {
        commentRepo.deleteById(id);
//        bookRepo.deleteCommentById(id);
    }
}
