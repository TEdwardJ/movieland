package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class DefaultMovieServiceTest {

    @Mock
    private MovieDao jdbcMovieDao;

    @Autowired
    @InjectMocks
    private MovieService movieService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    protected List<MovieDto> convertToDto(List<Movie> movieList){
        return movieList
                .stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> expectedMovies = new MovieLandTestDataGenerator().getMovies();
        when(jdbcMovieDao.getAll(any(MovieRequest.class))).thenReturn(expectedMovies);

        List<MovieDto> actualMovies = movieService.getAllMovies();

        assertEquals(expectedMovies.stream().map(MovieDto::new).collect(Collectors.toList()), actualMovies);
        reset(jdbcMovieDao);
    }

    @Test
    public void testGetThreeRandomMovies() {
        List<Movie> expectedMovies = new MovieLandTestDataGenerator().getMoviesForRandomTest();
        when(jdbcMovieDao.getThreeRandom()).thenReturn(expectedMovies.subList(0,3));

        List<MovieDto> actualMovies = movieService.getThreeRandomMovies();

        assertEquals(3,actualMovies.size());
    }

    @Test
    public void testGetByGenre() {
        List<Movie> expectedMovies = new MovieLandTestDataGenerator().getMovies();
        when(jdbcMovieDao.getMoviesByGenre(anyInt(),any(MovieRequest.class))).thenReturn(expectedMovies);

        List<MovieDto> actualMovies = movieService.getMoviesByGenre(2);

        assertEquals(convertToDto(expectedMovies), actualMovies);
        reset(jdbcMovieDao);
    }

    @Test
    public void testGetById() {
        Movie expectedMovie = new MovieLandTestDataGenerator().getMovies().get(0);
        when(jdbcMovieDao.getById(anyInt())).thenReturn(expectedMovie);

        MovieDto actualMovie = movieService.getById(2);
        assertEquals(new MovieDto(expectedMovie),actualMovie);
        reset(jdbcMovieDao);
    }

}