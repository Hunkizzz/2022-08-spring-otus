package ru.otus.homework.springdatajpa.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.springdatajpa.model.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
