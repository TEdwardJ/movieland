package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
@Repository
public class JdbcGenreDao implements GenreDao {

    private static final GenreRowMapper ROW_MAPPER = new GenreRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource dataSource;


    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String genreSelectAllQuery;

    @Override
    public List<Genre> getAll() {
        logger.debug("getting for all genres from DB");
        List<Genre> list =
                jdbcTemplate.query(genreSelectAllQuery, ROW_MAPPER);

        return list;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug("jdbcTemplate has been setup");
    }
}
