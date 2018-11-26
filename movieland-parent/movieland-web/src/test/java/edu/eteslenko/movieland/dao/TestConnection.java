package edu.eteslenko.movieland.dao;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class TestConnection {

    @Autowired
    DataSource dataSource;


    @Test
    public void testConnection() throws SQLException {
            Connection connection = dataSource.getConnection();

            assertTrue(connection.isValid(10));
    }
}
