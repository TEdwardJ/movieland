package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {

    private static final MovieRowMapper ROW_MAPPER = new MovieRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String movieSelectAllQuery;
    private String movieSelectTreeRandomQuery;
    private String movieSelectByGenreQuery;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setMovieSelectAllQuery(String movieSelectAllQuery) {
        this.movieSelectAllQuery = movieSelectAllQuery;
    }

    @Autowired
    public void setMovieSelectTreeRandomQuery(String movieSelectTreeRandomQuery) {
        this.movieSelectTreeRandomQuery = movieSelectTreeRandomQuery;
    }

    @Autowired
    public void setMovieSelectByGenreQuery(String movieSelectByGenreQuery) {
        this.movieSelectByGenreQuery = movieSelectByGenreQuery;
    }

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

/*    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug("jdbcTemplate has been setup");
    }*/

}
