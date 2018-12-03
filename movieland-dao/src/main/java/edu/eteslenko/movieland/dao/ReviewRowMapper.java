package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.MovieReview;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<MovieReview> {
    @Override
    public MovieReview mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieReview review = new MovieReview();
        review.setId(resultSet.getInt("review_id"));
        review.setText(resultSet.getString("message"));
        review.setUserId(resultSet.getInt("usr_id"));
        review.setMovieId(resultSet.getInt("m_id"));


        return review;
    }
}
