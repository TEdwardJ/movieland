package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.MovieReview;

import java.util.List;

public interface ReviewDao {
    List<MovieReview> getByMovieId(int id);
}
