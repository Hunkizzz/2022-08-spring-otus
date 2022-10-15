package ru.otus.education.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.education.jpalibraryapp.model.Author;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book_entity_graph");
        TypedQuery<Author> query = entityManager.createQuery("select author from Author author", Author.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        try {
            TypedQuery<Author> query = entityManager.createQuery("select author " +
                            "from Author author " +
                            "where author.name =:name and author.surname =:surname"
                    , Author.class);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}

