package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.MovieReview;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ReviewRowMapperTest {

    @Mock
    private ResultSet resultSet;

    private ReviewRowMapper rowMapper = new ReviewRowMapper();

    @Test
    public void mapRow() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getInt("review_id")).thenReturn(111);
        when(resultSet.getString("message")).thenReturn("Cool Movie");
        when(resultSet.getInt("usr_id")).thenReturn(12);
        when(resultSet.getInt("m_id")).thenReturn(21);
        MovieReview review = rowMapper.mapRow(resultSet,1);
        assertEquals(111,review.getId());
        assertEquals(21,review.getMovieId());
        assertEquals(12,review.getUserId());
        assertEquals("Cool Movie", review.getText());
    }
}