package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class JdbcCountryDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcCountryDao")
    @InjectMocks
    CountryDao jdbcGenreDao;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetAll() {
        List<Country> expectedCountries = new MovieLandTestDataGenerator().getCountries();

        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(CountryRowMapper.class))).thenReturn(expectedCountries);
        List<Country> actualCountries = jdbcGenreDao.getAll();
        Assert.assertEquals(expectedCountries,actualCountries);
    }

    @Test
    public void getByMovieId() {
    }
}