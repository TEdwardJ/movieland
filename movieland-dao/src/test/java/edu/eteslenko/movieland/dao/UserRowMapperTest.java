package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {
    @Mock
    private ResultSet resultSet;

    private UserRowMapper rowMapper = new UserRowMapper();

    @Test
    public void mapRow() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getInt("usr_id")).thenReturn(111);
        when(resultSet.getString("usr_email")).thenReturn("testuser@google.com");
        when(resultSet.getString("usr_name")).thenReturn("testUser");
        User user = rowMapper.mapRow(resultSet,1);
        assertEquals(111,user.getId());
        assertEquals("testuser@google.com",user.getEmail());
        assertEquals("testUser",user.getLogin());
    }
}