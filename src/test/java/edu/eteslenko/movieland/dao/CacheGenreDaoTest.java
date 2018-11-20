package edu.eteslenko.movieland.dao;

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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@DirtiesContext
public class CacheGenreDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcGenreDao")
    @InjectMocks
    GenreDao jdbcGenreDao;

    @Autowired
    GenreDao cacheGenreDao;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Genre> expectedGenres = new MovieLandTestDataGenerator().getGenres();
        when(jdbcTemplate.query(anyString(),any(RowMapper.class))).thenThrow(new AssertionError("GenreDao still asks DB for genres instead of using cache"));//.thenReturn(expectedGenres);
        List<Genre> actualGenres = cacheGenreDao.getAll();
        assertEquals(15,actualGenres.size());
        //Let's check my favorite genre
        assertTrue(actualGenres.stream().anyMatch(t->t.getName().equalsIgnoreCase("комедия")));
    }



}