package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {

    private static final MovieRowMapper ROW_MAPPER = new MovieRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource dataSource;


    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String movieSelectAllQuery;
    @Autowired
    private String movieSelectTreeRandomQuery;


    public List<Movie> getAll() {
        logger.debug("getting for all movies from DB");
        List<Movie> list =
                jdbcTemplate.query(movieSelectAllQuery, ROW_MAPPER);

        return list;
    }

    public List<Movie> getTreeRandom() {
        logger.debug("getting for 3 random movies from DB");
        List<Movie> list =
                jdbcTemplate.query(movieSelectTreeRandomQuery,ROW_MAPPER);

        //Just to be on the safe side,  explicitly limit output no more than 3 elements
        return list.subList(0, 3);
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug("jdbcTemplate has been setup");
    }

}
