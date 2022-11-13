package ru.otus.education.bookrepositorymongo.mongorepository.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.education.bookrepositorymongo.mongorepository.domain.Comment;
import ru.otus.education.bookrepositorymongo.mongorepository.repo.CommentRepo;

@Service
@Qualifier("commentDBService")
@RequiredArgsConstructor
public class CommentDBServiceImpl implements CommentDBService {
    private final SequenceGeneratorService sequenceGeneratorService;
    private final CommentRepo commentRepo;

    @Override
    public void delete(Comment comment) {
        commentRepo.delete(comment);
    }

    @Override
    public void save(Comment comment) {
        comment.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
        commentRepo.save(comment);
    }
}
