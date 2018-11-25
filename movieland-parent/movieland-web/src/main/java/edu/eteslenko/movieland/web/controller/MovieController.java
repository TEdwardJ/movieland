package edu.eteslenko.movieland.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.service.GenreService;
import edu.eteslenko.movieland.service.MovieService;
import edu.eteslenko.movieland.web.view.AllMoviesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;
    private GenreService genreService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/v1/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/v1/movie/random")
    public List<Movie> getTreeRandomMovies(){
        return movieService.get3RandomMovies();
    }

    @GetMapping(path = "/v1/genre")
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @JsonView(AllMoviesView.class)
    @GetMapping(path = "/v1/movie/genre/{id}")
    public List<Movie> getMovieByGenre(@PathVariable("id") int genre){
        return movieService.getMoviesByGenre(genre);
    }
}
