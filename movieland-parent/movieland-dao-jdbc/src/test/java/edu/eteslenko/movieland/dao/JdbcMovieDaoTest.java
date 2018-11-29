package edu.eteslenko.movieland.dao;

import static org.junit.Assert.*;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.OrderType;
import edu.eteslenko.movieland.entity.SortingColumn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JdbcMovieDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @InjectMocks
    MovieDao jdbcMovieDao;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Movie> expectedMovies = new MovieLandTestDataGenerator().getMovies();

        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(RowMapper.class))).thenReturn(expectedMovies);
        List<Movie> actualMovies = jdbcMovieDao.getAll();
        Assert.assertEquals(expectedMovies,actualMovies);

    }

    @Test
    public void testGetThreeRandom() throws SQLException {
        List<Movie> expectedMovies = new MovieLandTestDataGenerator().getMoviesForRandomTest();

        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(RowMapper.class))).thenReturn(expectedMovies);
        List<Movie> actualMovies = jdbcMovieDao.getThreeRandom();
        Assert.assertEquals(3,actualMovies.size());
    }

    @Test
    public void testGetByGenre() throws SQLException {
        List<Movie> expectedMovies = new MovieLandTestDataGenerator().getMovies();

        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(RowMapper.class), Matchers.anyInt())).thenReturn(expectedMovies);
        List<Movie> actualMovies = jdbcMovieDao.getMoviesByGenre(2);
        Assert.assertEquals(2,actualMovies.size());
    }

    @Test
    public void testPrepareQuery(){
        String sql = "SELECT * FROM MOVIE m;";
        String newSql1 = ((JdbcMovieDao)jdbcMovieDao).prepareQuery(sql,MovieRequest.DEFAULT);
        assertEquals(newSql1, sql);
        String newSql2 = ((JdbcMovieDao)jdbcMovieDao).prepareQuery(sql,new MovieRequest(SortingColumn.PRICE, OrderType.ASC));
        assertNotEquals(newSql2, sql);
        assertTrue(newSql2.contains("m_price ASC"));

        String newSql3 = ((JdbcMovieDao)jdbcMovieDao).prepareQuery(sql,new MovieRequest(SortingColumn.PRICE, OrderType.DESC));
        assertNotEquals(newSql3, sql);
        assertTrue(newSql3.contains("m_price DESC"));

    }
}

