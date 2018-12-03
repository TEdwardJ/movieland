package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.MovieLandTestDataGenerator;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class EnrichServicesTest {

    @Autowired
    private AbstractEnrichService defaultEnrichService;

    @Autowired
    private AbstractEnrichService parallelEnrichService;


    private void testService(AbstractEnrichService service, Movie expectedMovie){
        MovieDto testDto = new MovieDto(expectedMovie);
        service.enrich(testDto);
        assertEquals(expectedMovie.getId(),testDto.getId());
        assertEquals(expectedMovie.getDescription(),testDto.getDescription());
        assertEquals(expectedMovie.getPicturePath(),testDto.getPicturePath());
        assertEquals(expectedMovie.getPrice(),testDto.getPrice(),0);
        assertEquals(expectedMovie.getTitle(),testDto.getTitle());
        assertEquals(expectedMovie.getTitleInternational(),testDto.getTitleInternational());
        assertEquals(expectedMovie.getReleaseYear(),testDto.getReleaseYear());
        assertEquals(expectedMovie.getRating(),testDto.getRating(),0);
        assertNotEquals(0,testDto.getGenres().size());
        assertNotEquals(0,testDto.getCountries().size());
        assertNotEquals(0,testDto.getReviews().size());
    }

    @Test
    public void testDefaultEnrichService(){
        Movie expectedMovie = new MovieLandTestDataGenerator().getMovies().get(0);

        testService(defaultEnrichService, expectedMovie);
    }

    @Test
    public void testParallelEnrichService(){
        Movie expectedMovie = new MovieLandTestDataGenerator().getMovies().get(0);

        testService(parallelEnrichService, expectedMovie);
    }
}