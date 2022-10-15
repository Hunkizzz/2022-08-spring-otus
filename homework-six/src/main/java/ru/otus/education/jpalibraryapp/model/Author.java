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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"name", "surname"})
})
@NamedEntityGraph(name = "book_entity_graph", attributeNodes = {@NamedAttributeNode("books")})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Book> books;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
