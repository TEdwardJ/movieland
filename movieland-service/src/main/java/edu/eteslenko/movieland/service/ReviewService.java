package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.MovieReview;

import java.util.List;

public interface ReviewService {

    List<MovieReview> getMovieReviews(int id);
}
