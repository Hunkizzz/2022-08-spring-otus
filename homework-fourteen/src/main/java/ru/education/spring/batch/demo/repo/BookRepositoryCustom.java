package ru.education.spring.batch.demo.repo;

import ru.education.spring.batch.demo.domain.Book;

import java.math.BigInteger;

public interface BookRepositoryCustom {
    Book findBookByCommentId(BigInteger Id);
}
