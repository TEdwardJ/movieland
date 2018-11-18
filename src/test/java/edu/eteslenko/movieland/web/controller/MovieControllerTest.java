package edu.eteslenko.movieland.web.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/MovieDispatcher-servlet.xml",
    "classpath:testContext.xml"})
@WebAppConfiguration
public class MovieControllerTest {

    private ObjectMapper jsonMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MovieController movieController;

    public MovieControllerTest() {
    }

    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void getAllGenresTest() throws Exception {

        List<Genre> listGenre1 = performGenreRequest("/v1/genre",15);
        List<Genre> listGenre2 = performGenreRequest("/v1/genre",15);
        assertEquals(listGenre1,listGenre2);
        assertEquals(15,listGenre1.size());
        assertEquals(15,listGenre2.size());
    }

    @Test
    public void getAllMoviesTest() throws Exception {

        List<Movie> listMovie1 = performMovieRequest("/v1/movies",5);
        List<Movie> listMovie2 = performMovieRequest("/v1/movies",5);
        assertEquals(listMovie1,listMovie2);
        assertEquals(5,listMovie1.size());
        assertEquals(5,listMovie2.size());
    }

    @Test
    public void getTreeRandomTest() throws Exception {

        List<Movie> listMovie1 = performMovieRequest("/v1/movie/random", 3);
        List<Movie> listMovie2 = performMovieRequest("/v1/movie/random", 3);

        assertNotEquals(listMovie2,listMovie1);
    }

    private List<Genre> performGenreRequest(String s, int i) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(s);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(i)));
        MvcResult mvcResult = result.andReturn();
        String jsonMovieArray = mvcResult.getResponse().getContentAsString();
        return jsonMapper.readValue(jsonMovieArray, new TypeReference<List<Genre>>() {
        });
    }

    private List<Movie> performMovieRequest(String s, int i) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(s);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(i)));
        MvcResult mvcResult = result.andReturn();
        String jsonMovieArray = mvcResult.getResponse().getContentAsString();
        return jsonMapper.readValue(jsonMovieArray, new TypeReference<List<Movie>>() {
        });
    }

}