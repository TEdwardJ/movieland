package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.dao.GenreDao;
import edu.eteslenko.movieland.entity.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class DefaultGenreServiceTest {

    @Mock
    private GenreDao jdbcGenreDao;

    @Autowired
    @InjectMocks
    private GenreService genreService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMoviesTest() {
        List<Genre> expectedGenres = new MovieLandTestDataGenerator().getGenres();
        when(jdbcGenreDao.getAll()).thenReturn(expectedGenres);

        List<Genre> actualGenres = genreService.getAllGenres();

        assertEquals(expectedGenres,actualGenres);
        assertFalse(expectedGenres.size()==0);
    }


}