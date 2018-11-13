package edu.eteslenko.movieland.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml","file:src/main/webapp/WEB-INF/MovieDispatcher-servlet.xml",
})
@WebAppConfiguration
public class MovieControllerTest {

    MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    @InjectMocks
    MovieController movieController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setPicturePath("test");
        movie1.setRating(8);
        movie1.setDescription("test test");
        movie1.setReleaseYear(1998);
        movie1.setPrice(123);
        movie1.setTitle("Test Movie");
        movie1.setTitleEng("Test Movie");
        expectedMovies.add(movie1);
        Movie movie2 = new Movie();
        movie2.setPicturePath("test2");
        movie2.setRating(9);
        movie2.setDescription("test test2");
        movie2.setReleaseYear(2001);
        movie2.setPrice(321);
        movie2.setTitle("Test Movie2");
        movie1.setTitleEng("Test Movie2");
        expectedMovies.add(movie2);
        when(movieService.getAllMovies()).thenReturn(expectedMovies);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/movies");

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper movieMapper = new ObjectMapper();

        ResultActions result = mockMvc.perform(request);
        //restTemplate.

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));
        MvcResult mvcResult = result.andReturn();
        String jsonMovieArray = mvcResult.getResponse().getContentAsString();

        List<Movie> listMovie = movieMapper.readValue(jsonMovieArray, new TypeReference<List<Movie>>(){});
        expectedMovies.stream().forEach(t->t.setDescription(null));
        assertEquals(expectedMovies,listMovie);
    }
}