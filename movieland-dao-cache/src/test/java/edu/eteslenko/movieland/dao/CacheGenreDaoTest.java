package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:testContext.xml"})
public class CacheGenreDaoTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcGenreDao")
    GenreDao jdbcGenreDao;

    @Autowired
    GenreDao cacheGenreDao;

    @Test
    public void testGetAll() throws UnsupportedEncodingException {
        //Doing mock at this place we suppose that first cache refresh is done already
        jdbcTemplate = mock(JdbcTemplate.class);
        //so jdbcTemplateDao should not be called
        when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                .thenThrow(new AssertionError("GenreDao still asks DB for genres instead of using cache"));
        ((JdbcGenreDao) jdbcGenreDao).setJdbcTemplate(jdbcTemplate);

        List<Genre> actualGenresCache = cacheGenreDao.getAll();
        assertEquals(15, actualGenresCache.size());
        //Let's check my favorite genre
        final String searchString = new String("комедия");
        assertTrue(actualGenresCache.stream().anyMatch(t -> t.getName().equals(searchString)));
    }


}