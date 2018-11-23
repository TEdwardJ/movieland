package edu.eteslenko.movieland.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.entity.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class JdbcGenreDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcGenreDao")
    @InjectMocks
    GenreDao jdbcGenreDao;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Genre> expectedGenres = new MovieLandTestDataGenerator().getGenres();

        when(jdbcTemplate.query(anyString(),any(RowMapper.class))).thenReturn(expectedGenres);
        List<Genre> actualGenres = jdbcGenreDao.getAll();
        assertEquals(expectedGenres,actualGenres);
    }


}

