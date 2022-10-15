package ru.otus.education.bookrepositoryjdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.education.bookrepositoryjdbc.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public void insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(NAME_COLUMN, author.getName());
        params.addValue(SURNAME_COLUMN, author.getSurname());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into author (name, surname) values(:name, :surname)", params, keyHolder);
    }

    @Override
    public Author getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        return jdbcOperations.queryForObject("select * from author where id = :id",
                params, new AuthorMapper());
    }

    @Override
    public boolean checkByNameAndSurname(String authorName, String authorSurname) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put(NAME_COLUMN, authorName);
        params.put(SURNAME_COLUMN, authorSurname);
        List<Author> queryResult = jdbcOperations.query("select * from author where name = :name and surname = :surname",
                params, new AuthorMapper());
        return !queryResult.isEmpty();
    }

    @Override
    public Author getByNameAndSurname(String authorName, String authorSurname) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put(NAME_COLUMN, authorName);
        params.put(SURNAME_COLUMN, authorSurname);
        return jdbcOperations.queryForObject("select * from author where name = :name and surname = :surname",
                params, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString(NAME_COLUMN);
            String surname = resultSet.getString(SURNAME_COLUMN);
            return new Author(id, name, surname);
        }
    }
}
