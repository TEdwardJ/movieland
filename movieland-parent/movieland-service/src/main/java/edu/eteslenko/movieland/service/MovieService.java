package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(MovieRequest movieRequest);

    List<Movie> getAllMovies();

    List<Movie> getThreeRandomMovies(MovieRequest movieRequest);

    List<Movie> getThreeRandomMovies();

    List<Movie> getMoviesByGenre(int genre);

    List<Movie> getMoviesByGenre(int genre, MovieRequest movieRequest);
}
