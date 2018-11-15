package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.MovieTestDataGenerator;
import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class DefaulltMovieServiceTest {

    @Mock
    private MovieDao jdbcMovieDao;

    @Autowired
    @InjectMocks
    private MovieService movieService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMoviesTest() {
        List<Movie> expectedMovies = new MovieTestDataGenerator().getMovies();
        when(jdbcMovieDao.getAll()).thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.getAllMovies();

        assertEquals(expectedMovies,actualMovies);
    }

    @Test
    public void get3RandomMoviesTest() {
        List<Movie> expectedMovies = new MovieTestDataGenerator().getMoviesForRandomTest();
        when(jdbcMovieDao.get3Random()).thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.get3RandomMovies();

        assertEquals(3,actualMovies.size());
    }
}