package ru.education.spring.batch.demo.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.education.spring.batch.demo.domain.Comment;
import ru.education.spring.batch.demo.dto.CommentDTO;
import ru.education.spring.batch.demo.repo.BookRepository;

@Component
@RequiredArgsConstructor
public class CommentDTOProcessor implements ItemProcessor<Comment, CommentDTO> {

    private final BookRepository bookRepository;

    public CommentDTO process(Comment comment) throws Exception {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setBookId(bookRepository.findBookByCommentId(comment.getId()).getId());
        return commentDTO;
    }

}
