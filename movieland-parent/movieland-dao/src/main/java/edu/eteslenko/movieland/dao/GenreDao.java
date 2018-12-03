package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    List<Genre> getByMovieId(int id);
}

