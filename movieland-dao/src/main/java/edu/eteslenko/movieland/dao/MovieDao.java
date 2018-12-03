package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(MovieRequest movieRequest);

    List<Movie> getAll();

    List<Movie> getThreeRandom(MovieRequest movieRequest);

    List<Movie> getThreeRandom();

    List<Movie> getMoviesByGenre(int genre);

    List<Movie> getMoviesByGenre(int genre, MovieRequest movieRequest);

    Movie getById(int id);
}
