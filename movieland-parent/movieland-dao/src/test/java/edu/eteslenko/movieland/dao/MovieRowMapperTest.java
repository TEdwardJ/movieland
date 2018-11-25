package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {

    @Mock
    private ResultSet resultSet;

    private MovieRowMapper rowMapper = new MovieRowMapper();

    @Test
    public void mapRow() throws SQLException {
        MovieLandTestDataGenerator generator = new MovieLandTestDataGenerator();
        Movie testMovie = generator.getMovies().get(0);
        MockitoAnnotations.initMocks(this);

        when(resultSet.getInt("m_id")).thenReturn(testMovie.getId());
        when(resultSet.getString("m_title")).thenReturn(testMovie.getTitle());
        when(resultSet.getString("m_title_en")).thenReturn(testMovie.getTitleInternational());
        when(resultSet.getDouble("m_price")).thenReturn(testMovie.getPrice());
        when(resultSet.getInt("m_release_year")).thenReturn(testMovie.getReleaseYear());
        when(resultSet.getString("m_description")).thenReturn(testMovie.getDescription());
        when(resultSet.getDouble("m_rating")).thenReturn(testMovie.getRating());
        when(resultSet.getString("picture_url")).thenReturn(testMovie.getPicturePath());
        Movie movie = rowMapper.mapRow(resultSet,1);
        assertEquals(movie,testMovie);
    }
}