package ru.otus.education.bookrepositoryjdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.education.bookrepositoryjdbc.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public void insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into genre (name) values(:name)", params, keyHolder);

    }

    @Override
    public Genre getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        return jdbcOperations.queryForObject("select * from genre where id = :id",
                params, new GenreMapper());
    }

    @Override
    public boolean checkByName(String genreName) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("name", genreName);
        List<Genre> queryResult = jdbcOperations.query("select * from genre where name = :name",
                params, new GenreMapper());
        return !queryResult.isEmpty();
    }

    @Override
    public Genre getByName(String genreName) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("name", genreName);
        return jdbcOperations.queryForObject("select * from genre where name = :name",
                params, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
