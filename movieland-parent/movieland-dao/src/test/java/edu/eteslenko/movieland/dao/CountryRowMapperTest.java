package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Country;
import edu.eteslenko.movieland.entity.User;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CountryRowMapperTest {

    @Mock
    private ResultSet resultSet;

    private CountryRowMapper rowMapper = new CountryRowMapper();

    @Test
    public void mapRow() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getInt("cntr_id")).thenReturn(111);
        when(resultSet.getString("cntr_name")).thenReturn("USA");
        Country country = rowMapper.mapRow(resultSet,1);
        assertEquals(111,country.getId());
        assertEquals("USA",country.getName());
    }
}