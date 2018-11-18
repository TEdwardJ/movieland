package edu.eteslenko.movieland.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.service.MovieService;
import edu.eteslenko.movieland.web.view.AllMovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

   @JsonView(AllMovieView.class)
    @GetMapping(path = "/v1/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @JsonView(AllMovieView.class)
    @GetMapping(path = "/v1/movie/random")
    public List<Movie> getTreeRandomMovies(){
        return movieService.get3RandomMovies();
    }
}
