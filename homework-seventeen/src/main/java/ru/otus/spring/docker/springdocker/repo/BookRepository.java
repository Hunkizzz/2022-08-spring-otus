package ru.otus.spring.docker.springdocker.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.docker.springdocker.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "author_genre_entity_graph")
    List<Book> findAll();

    void deleteBookById(Long id);

}
