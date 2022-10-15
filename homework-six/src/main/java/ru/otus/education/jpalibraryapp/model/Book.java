package ru.otus.education.jpalibraryapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "author_genre_entity_graph", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title, Genre genre, Author author) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return id +
                " Наименование: " + title +
                ". Имя автора: " + author.getName() +
                ". Фамилия автора: " + author.getSurname() +
                ". Жанр: " + genre.getName();
    }
}
