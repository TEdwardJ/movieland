package edu.eteslenko.movieland;

import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;

import java.util.List;

public interface MovieDataGenerator {

    List<Movie> getMovies();

    List<Genre> getGenres();
}
