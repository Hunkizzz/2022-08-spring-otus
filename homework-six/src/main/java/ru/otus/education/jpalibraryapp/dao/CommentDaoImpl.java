package ru.otus.education.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.education.jpalibraryapp.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public void deleteById(Comment comment) {
        entityManager.remove(comment);
    }

    @Override
    public List<Comment> findAllCommentsByAuthorId(long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select comment " +
                "from Comment comment " +
                "left join comment.book book where book.author.id=:id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
