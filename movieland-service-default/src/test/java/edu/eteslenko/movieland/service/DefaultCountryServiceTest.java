package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.dao.CountryDao;
import edu.eteslenko.movieland.entity.Country;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class DefaultCountryServiceTest {

    @Mock
    private CountryDao jdbcGenreDao;

    @Autowired
    @InjectMocks
    private CountryService countryService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetAllCountries() {
        List<Country> expectedCountries = new MovieLandTestDataGenerator().getCountries();
        when(countryService.getAllCountries()).thenReturn(expectedCountries);

        List<Country> actualGenres = countryService.getAllCountries();

        assertEquals(expectedCountries,actualGenres);
        assertFalse(expectedCountries.size()==0);
    }

    @Test
    public void testGetCountriesByMovieId() {
        List<Country> expectedCountries = new MovieLandTestDataGenerator().getCountries();
        when(countryService.getCountriesByMovieId(anyInt())).thenReturn(expectedCountries);

        List<Country> actualGenres = countryService.getCountriesByMovieId(1);

        assertEquals(expectedCountries,actualGenres);
        assertFalse(expectedCountries.size()==0);
    }
}