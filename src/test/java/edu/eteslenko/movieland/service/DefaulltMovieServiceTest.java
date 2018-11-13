package edu.eteslenko.movieland.service;

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
//@WebAppConfiguration
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
    public void getAllMovies() {
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setPicturePath("test");
        movie1.setRating(8);
        movie1.setDescription("test test");
        movie1.setReleaseYear(1998);
        movie1.setPrice(123);
        movie1.setTitle("Test Movie");
        expectedMovies.add(movie1);
        Movie movie2 = new Movie();
        movie2.setPicturePath("test2");
        movie2.setRating(9);
        movie2.setDescription("test test2");
        movie2.setReleaseYear(2001);
        movie2.setPrice(321);
        movie2.setTitle("Test Movie2");
        expectedMovies.add(movie2);
        when(jdbcMovieDao.getAll()).thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.getAllMovies();

        assertEquals(expectedMovies,actualMovies);
    }
}