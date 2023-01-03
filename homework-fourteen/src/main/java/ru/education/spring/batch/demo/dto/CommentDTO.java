package ru.education.spring.batch.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.education.spring.batch.demo.domain.Comment;

import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO extends Comment {
    private BigInteger bookId;
}
