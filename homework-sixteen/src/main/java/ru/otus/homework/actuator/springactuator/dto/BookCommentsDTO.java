package ru.otus.homework.actuator.springactuator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.homework.actuator.springactuator.domain.Book;
import ru.otus.homework.actuator.springactuator.domain.Comment;

import java.util.List;

@AllArgsConstructor
@Data
public class BookCommentsDTO {
    Book book;
    List<Comment> comments;

}
