package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieQuery;
import edu.eteslenko.movieland.entity.OrderType;
import edu.eteslenko.movieland.entity.SortingColumn;
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
    private String movieSelectThreeRandomQuery;
    private String movieSelectByGenreQuery;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setMovieSelectAllQuery(String movieSelectAllQuery) {
        this.movieSelectAllQuery = movieSelectAllQuery;
    }

    public List<Movie> getAll() {
        return getAll(MovieQuery.DEFAULT);
    }



    public List<Movie> getAll(MovieQuery movieQuery) {
        logger.debug("getting for all movies from DB");
        String actualQuery = prepareQuery(movieSelectAllQuery,movieQuery);
        List<Movie> list =
                jdbcTemplate.query(actualQuery, ROW_MAPPER);

        return list;
    }

    private String prepareQuery(String initialQuery, MovieQuery movieQuery) {
        if (movieQuery == MovieQuery.DEFAULT){
            return initialQuery;
        }
        logger.debug("MovieQuery is not default, so preparing query is needed");
        return new StringBuilder(initialQuery.replace(";"," ORDER BY "))
                .append(" ")
                .append(movieQuery.getSortingColumn().getSortingColumn())
                .append(" ")
                .append(movieQuery.getOrderType())
                .append(";")
                .toString();
    }

    @Override
    public List<Movie> getThreeRandom(MovieQuery movieQuery) {
        logger.debug("getting for 3 random movies from DB");
        String actualQuery = prepareQuery(movieSelectThreeRandomQuery,movieQuery);
        List<Movie> list =
                jdbcTemplate.query(actualQuery, ROW_MAPPER);
        return list;
    }

    public List<Movie> getThreeRandom() {
        return getThreeRandom(MovieQuery.DEFAULT);
    }

    public List<Movie> getMoviesByGenre(int genre) {
        return getMoviesByGenre(genre, MovieQuery.DEFAULT);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre, MovieQuery movieQuery) {
        logger.debug("Getting for movies from DB by genre {}",genre);
        String actualQuery = prepareQuery(movieSelectByGenreQuery,movieQuery);
        List<Movie> list =
                jdbcTemplate.query(actualQuery, ROW_MAPPER, genre);
        return list;
    }

    @Autowired
    public void setMovieSelectThreeRandomQuery(String movieSelectThreeRandomQuery) {
        this.movieSelectThreeRandomQuery = movieSelectThreeRandomQuery;
    }

    @Autowired
    public void setMovieSelectByGenreQuery(String movieSelectByGenreQuery) {
        this.movieSelectByGenreQuery = movieSelectByGenreQuery;
    }

}
