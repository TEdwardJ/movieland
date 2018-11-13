package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();
}
