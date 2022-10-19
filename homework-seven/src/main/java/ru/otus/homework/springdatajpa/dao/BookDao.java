package ru.otus.homework.springdatajpa.dao;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework.springdatajpa.model.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph(value = "author_genre_entity_graph")
    List<Book> findAll();

    @EntityGraph(value = "author_genre_entity_graph")
    List<Book> findBooksByTitle(String title);

    @EntityGraph(value = "author_genre_entity_graph")
    List<Book> findAllByAuthorId(long id);

    @Query("select new org.apache.commons.lang3.tuple.ImmutablePair (book, count(comment))" +
            "                        from Book book left join Comment comment on book.id = comment.book.id " +
            "                        group by comment.book")
    List<ImmutablePair<Book, Long>> findAllBooksWithCommentsCount();
}
