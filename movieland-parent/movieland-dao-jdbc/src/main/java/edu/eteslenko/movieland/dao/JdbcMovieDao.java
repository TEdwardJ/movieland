package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcMovieDao implements MovieDao {

    private Map<String, String> columnMapping = new HashMap<>();
    private static final MovieRowMapper ROW_MAPPER = new MovieRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String movieSelectAllQuery;
    private String movieSelectThreeRandomQuery;
    private String movieSelectByGenreQuery;

    private String movieSelectByIdQuery;

    public List<Movie> getAll() {
        return getAll(MovieRequest.DEFAULT);
    }

    public List<Movie> getAll(MovieRequest movieRequest) {
        logger.debug("getting for all movies from DB");
        String actualQuery = prepareQuery(movieSelectAllQuery, movieRequest);
        List<Movie> list =
                jdbcTemplate.query(actualQuery, ROW_MAPPER);

        return list;
    }

    protected String prepareQuery(String initialQuery, MovieRequest movieRequest) {
        if (movieRequest == MovieRequest.DEFAULT){
            return initialQuery;
        }
        logger.debug("MovieRequest is not default, so preparing query is needed");
        return new StringBuilder(initialQuery.replace(";"," ORDER BY "))
                .append(" ")
                .append(columnMapping.get(movieRequest.getSortingColumn().name()))
                .append(" ")
                .append(movieRequest.getOrderType().name())
                .append(";")
                .toString();
    }

    @Override
    public List<Movie> getThreeRandom(MovieRequest movieRequest) {
        logger.debug("getting for 3 random movies from DB");
        String actualQuery = prepareQuery(movieSelectThreeRandomQuery, movieRequest);
        List<Movie> list =
                jdbcTemplate.query(actualQuery, ROW_MAPPER);
        return list;
    }

    public List<Movie> getThreeRandom() {
        return getThreeRandom(MovieRequest.DEFAULT);
    }

    public List<Movie> getMoviesByGenre(int genre) {
        return getMoviesByGenre(genre, MovieRequest.DEFAULT);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre, MovieRequest movieRequest) {
        logger.debug("Getting for movies from DB by genre {}",genre);
        String actualQuery = prepareQuery(movieSelectByGenreQuery, movieRequest);
        List<Movie> list =
                jdbcTemplate.query(actualQuery, ROW_MAPPER, genre);
        return list;
    }

    @Override
    public Movie getById(int id) {
        logger.debug("Getting for movies from DB by movie id {}", id);
        Movie movie =
                jdbcTemplate.query(movieSelectByIdQuery,
                                   t-> t.next()?ROW_MAPPER.mapRow(t,1):null,
                                   new Integer(id));
        return movie;
    }

    @Autowired
    public void setMovieSelectThreeRandomQuery(String movieSelectThreeRandomQuery) {
        this.movieSelectThreeRandomQuery = movieSelectThreeRandomQuery;
    }

    @Autowired
    public void setMovieSelectByGenreQuery(String movieSelectByGenreQuery) {
        this.movieSelectByGenreQuery = movieSelectByGenreQuery;
    }

    @Autowired
    public void setMovieSelectAllQuery(String movieSelectAllQuery) {
        this.movieSelectAllQuery = movieSelectAllQuery;
    }

    @Autowired
    public void setMovieSelectByIdQuery(String movieSelectByIdQuery) {
        this.movieSelectByIdQuery = movieSelectByIdQuery;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostConstruct
    public void initMapping(){
        columnMapping.put("RATING","m_rating");
        columnMapping.put("PRICE","m_price");
        columnMapping = Collections.unmodifiableMap(columnMapping);
    }
}
