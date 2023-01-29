package ru.otus.spring.docker.springdocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.docker.springdocker.domain.Book;
import ru.otus.spring.docker.springdocker.domain.Comment;

import java.util.List;

@AllArgsConstructor
@Data
public class BookCommentsDTO {
    Book book;
    List<Comment> comments;

}
