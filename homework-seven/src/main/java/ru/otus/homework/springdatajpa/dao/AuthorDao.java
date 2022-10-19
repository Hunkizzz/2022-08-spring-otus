package ru.otus.homework.springdatajpa.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.springdatajpa.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long> {
    @EntityGraph(value = "book_entity_graph")
    List<Author> findAll();

    Optional<Author> findByNameAndSurname(String name, String surname);
}
