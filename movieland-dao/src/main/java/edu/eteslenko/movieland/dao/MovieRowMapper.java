package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("m_id"));
        movie.setTitle(resultSet.getString("m_title"));
        movie.setTitleInternational(resultSet.getString("m_title_en"));
        movie.setPrice(resultSet.getDouble("m_price"));
        movie.setReleaseYear(resultSet.getInt("m_release_year"));
        movie.setDescription(resultSet.getString("m_description"));
        movie.setRating(resultSet.getDouble("m_rating"));
        movie.setPicturePath(resultSet.getString("picture_url"));
        return movie;
    }
}