package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieQuery;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(MovieQuery movieQuery);

    List<Movie> getAllMovies();

    List<Movie> getThreeRandomMovies(MovieQuery movieQuery);

    List<Movie> getThreeRandomMovies();

    List<Movie> getMoviesByGenre(int genre);

    List<Movie> getMoviesByGenre(int genre, MovieQuery movieQuery);
}
