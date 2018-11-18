package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    List<Movie> get3RandomMovies();

    List<Movie> getMoviesByGenre(int genre);
}
