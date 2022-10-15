package ru.otus.education.jpalibraryapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.education.jpalibraryapp.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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
    public List<Comment> findByBookId(long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select comment " +
                "from Comment comment " +
                "where comment.book.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void updateTextById(long id, String text) {
        Query query = entityManager.createQuery("update Comment comment " +
                "set comment.text=:text " +
                "where comment.id=:id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete " +
                "from Comment comment " +
                "where comment.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAllCommentsByAuthorId(long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select comment " +
                "from Comment comment " +
                "left join comment.book book where book.author.id=:id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void deleteByBookId(long id) {
        Query query = entityManager.createQuery("delete " +
                "from Comment comment " +
                "where comment.book.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
