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


    public List<Movie> getAll() {
        final List<Movie> list =

                jdbcTemplate.query(
                        movieSelectAllQuery,
                        (rs, rowNum) -> ROW_MAPPER.getRow(rs)
                );

        return list;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }




}
