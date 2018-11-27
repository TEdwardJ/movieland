package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieQuery;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(MovieQuery movieQuery);

    List<Movie> getAll();

    List<Movie> getThreeRandom(MovieQuery movieQuery);

    List<Movie> getThreeRandom();

    List<Movie> getMoviesByGenre(int genre);

    List<Movie> getMoviesByGenre(int genre, MovieQuery movieQuery);
}
