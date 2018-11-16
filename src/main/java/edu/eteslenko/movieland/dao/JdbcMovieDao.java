package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {

    private static final MovieRowMapper ROW_MAPPER = new MovieRowMapper();

    @Autowired
    private DataSource dataSource;


    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String movieSelectAllQuery;
    @Autowired
    private String movieSelect3RandomQuery;


    public List<Movie> getAll() {
        final List<Movie> list =
                jdbcTemplate.query(
                        movieSelectAllQuery,
                        (rs, rowNum) -> ROW_MAPPER.getRow(rs)
                );

        return list;
    }

    public List<Movie> get3Random() {
        final List<Movie> list =
                jdbcTemplate.query(
                        movieSelect3RandomQuery,
                        (rs, rowNum) -> ROW_MAPPER.getRow(rs)
                );

        //Just to be on the safe side,  explicitly limit output no more than 3 elements
        return list.subList(0,3);
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




}
