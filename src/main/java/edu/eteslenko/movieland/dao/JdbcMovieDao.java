package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
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

    @Autowired
    private String movieSelectByGenreQuery;


    public List<Movie> getAll() {
        logger.debug("getting for all movies from DB");
        List<Movie> list =
                jdbcTemplate.query(movieSelectAllQuery, ROW_MAPPER);

        return list;
    }

    public List<Movie> getThreeRandom() {
        logger.debug("getting for 3 random movies from DB");
        List<Movie> list =
                jdbcTemplate.query(movieSelectTreeRandomQuery,ROW_MAPPER);

        //Just to be on the safe side,  explicitly limit output no more than 3 elements
        return list.subList(0, 3);
    }

    public List<Movie> getMoviesByGenre(int genre) {
        logger.debug("Getting for movies from DB by genre {}",genre);
        List<Movie> list =
                jdbcTemplate.query(movieSelectByGenreQuery, ROW_MAPPER, genre);

        return list;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug("jdbcTemplate has been setup");
    }

}
