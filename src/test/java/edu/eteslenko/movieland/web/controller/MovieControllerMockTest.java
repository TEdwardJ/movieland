package edu.eteslenko.movieland.web.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eteslenko.movieland.MovieTestDataGenerator;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/MovieDispatcher-servlet.xml",
        "classpath:testContext.xml"})
@WebAppConfiguration
public class MovieControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    @InjectMocks
    private MovieController movieController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        List<Movie> expectedMovies = new MovieTestDataGenerator().getMovies();
        when(movieService.getAllMovies()).thenReturn(expectedMovies);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/movies");

        ObjectMapper movieMapper = new ObjectMapper();

        ResultActions result = mockMvc.perform(request);

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