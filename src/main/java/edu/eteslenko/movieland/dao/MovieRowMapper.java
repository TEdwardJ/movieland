package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRowMapper {


    public Movie getRow(ResultSet resultSet) throws SQLException {
        Movie product;
        product = mapMovie(resultSet);
        return product;
    }
    private Movie mapMovie(ResultSet resultSet) throws SQLException {
        Movie movie;
        movie = new Movie();
        movie.setId(resultSet.getInt("m_id"));
        movie.setTitle(resultSet.getString("m_title"));
        movie.setTitleEng(resultSet.getString("m_title_en"));
        movie.setPrice(resultSet.getDouble("m_price"));
        movie.setReleaseYear(resultSet.getInt("m_release_year"));
        movie.setDescription(resultSet.getString("m_description"));
        movie.setRating(resultSet.getDouble("m_rating"));
        movie.setPicturePath(resultSet.getString("picture_url"));
        return movie;
    }
}