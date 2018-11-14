package edu.eteslenko.movieland.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.eteslenko.movieland.MovieTestDataGenerator;
import edu.eteslenko.movieland.entity.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
//@WebAppConfiguration
public class JdbcMovieDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @InjectMocks
    MovieDao jdbcMovieDao;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() throws SQLException {
        List<Movie> expectedMovies = new MovieTestDataGenerator().getMovies();

        when(jdbcTemplate.query(anyString(),any(RowMapper.class))).thenReturn(expectedMovies);
        List<Movie> actualMovies = jdbcMovieDao.getAll();
        assertEquals(expectedMovies,actualMovies);

    }
}

