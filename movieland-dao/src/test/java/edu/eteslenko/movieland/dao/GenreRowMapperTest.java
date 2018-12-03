package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {

    @Mock
    private ResultSet resultSet;

    private GenreRowMapper rowMapper = new GenreRowMapper();

    @Test
    public void mapRow() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getInt("gnr_id")).thenReturn(111);
        when(resultSet.getString("gnr_name")).thenReturn("test_genre");
        Genre genre = rowMapper.mapRow(resultSet,1);
        assertEquals(genre.getId(),111);
        assertEquals(genre.getName(),"test_genre");
    }
}