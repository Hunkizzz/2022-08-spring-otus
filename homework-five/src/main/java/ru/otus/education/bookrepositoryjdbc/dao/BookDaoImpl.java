package ru.otus.education.bookrepositoryjdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.education.bookrepositoryjdbc.model.Author;
import ru.otus.education.bookrepositoryjdbc.model.Book;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int getCount() {
        return jdbcOperations.queryForObject("select count(*) from book", new HashMap<>(1), Integer.class);
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("authorId", book.getAuthor().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into book (title, genreid, authorId) values(:title, :genreId, :authorId)", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Book getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbcOperations.queryForObject("select b.id," +
                        " b.title" +
                        ", b.genreId" +
                        ", b.authorId" +
                        ", a.name authorName" +
                        ", a.surname authorSurname" +
                        ",g.name genreName " +
                        "from (book b left join author a on b.authorId = a.id) " +
                        "left join genre g on b.genreId = g.id " +
                        "where b.id = :id",
                params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select b.id, b.title, b.genreId, b.authorId, a.name authorName, a.surname authorSurname, g.name genreName " +
                        "from (book b left join author a on b.authorId = a.id) " +
                        "left join genre g on b.genreId = g.id",
                new BookMapper());
    }

    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbcOperations.update("delete from book where id=:id", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Book book = new Book(id, title);
            book.setAuthor(new Author(resultSet.getLong("authorId")
                    , resultSet.getString("authorName")
                    , resultSet.getString("authorSurname")));
            book.setGenre(new Genre(resultSet.getLong("genreId")
                    , resultSet.getString("genreName")));
            return book;
        }
    }


}
