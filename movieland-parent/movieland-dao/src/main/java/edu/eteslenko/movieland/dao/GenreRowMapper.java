package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre(resultSet.getInt("gnr_id"), resultSet.getString("gnr_name"));
        return genre;
    }
}
