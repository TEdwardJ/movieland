package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreDao implements GenreDao {

    private static final GenreRowMapper ROW_MAPPER = new GenreRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String genreSelectAllQuery;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setGenreSelectAllQuery(String genreSelectAllQuery) {
        this.genreSelectAllQuery = genreSelectAllQuery;
    }

    @Override
    public List<Genre> getAll() {
        logger.debug("Getting all genres from DB");
        List<Genre> list =
                jdbcTemplate.query(genreSelectAllQuery, ROW_MAPPER);

        return list;
    }

}
