package edu.eteslenko.movieland.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.*;
import edu.eteslenko.movieland.entity.dto.MovieDto;
import edu.eteslenko.movieland.service.GenreService;
import edu.eteslenko.movieland.service.MovieService;
import edu.eteslenko.movieland.web.view.AllMoviesView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static edu.eteslenko.movieland.web.MovieQueryFactory.getMovieQuery;

@RestController
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private MovieService movieService;
    private GenreService genreService;

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getAllMovies(@RequestParam(required = false) HashMap<String, String> params) {
        MovieRequest movieRequest = getMovieQuery(params);
        logger.debug("MovieRequest is {}", movieRequest);
        return movieService.getAllMovies(movieRequest);
    }

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/movie/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getThreeRandomMovies(@RequestParam(required = false) HashMap<String, String> params) {
        MovieRequest movieRequest = getMovieQuery(params);
        logger.debug("MovieRequest is {}", movieRequest);
        return movieService.getThreeRandomMovies(movieRequest);
    }


    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/movie/genre/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getMovieByGenre(@PathVariable("id") int genre,
                                          @RequestParam(required = false) HashMap<String, String> params) {
        MovieRequest movieRequest = getMovieQuery(params);
        logger.debug("MovieRequest is {}", movieRequest);
        return movieService.getMoviesByGenre(genre, movieRequest);
    }

    @GetMapping(path = "/movie/{movieId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @JsonView(DetailedMovieView.class)
    public ResponseEntity<MovieDto> getMovieById(@PathVariable int movieId) {
        Optional<MovieDto> movie = Optional.ofNullable(movieService.getById(movieId));
        return new ResponseEntity<MovieDto>(movie.get(), movie.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
}
