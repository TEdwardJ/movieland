package edu.eteslenko.movieland.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.*;
import edu.eteslenko.movieland.service.GenreService;
import edu.eteslenko.movieland.service.MovieService;
import edu.eteslenko.movieland.web.view.AllMoviesView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static edu.eteslenko.movieland.web.MovieQueryFactory.getMovieQuery;

@RestController
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private MovieService movieService;
    private GenreService genreService;

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/v1/movies")
    public List<Movie> getAllMovies(@RequestParam(required = false) HashMap<String, String> params) {
        MovieQuery movieQuery = getMovieQuery(params);
        logger.debug("MovieQuery is {}", movieQuery);
        return movieService.getAllMovies(movieQuery);
    }

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/v1/movie/random")
    public List<Movie> getThreeRandomMovies(@RequestParam(required = false) HashMap<String, String> params) {
        MovieQuery movieQuery = getMovieQuery(params);
        logger.debug("MovieQuery is {}", movieQuery);
        return movieService.getThreeRandomMovies(movieQuery);
    }

    @GetMapping(path = "/v1/genre")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/v1/movie/genre/{id}")
    public List<Movie> getMovieByGenre(@PathVariable("id") int genre,
                                       @RequestParam(required = false) HashMap<String, String> params) {
        MovieQuery movieQuery = getMovieQuery(params);
        logger.debug("MovieQuery is {}", movieQuery);
        return movieService.getMoviesByGenre(genre, movieQuery);
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
