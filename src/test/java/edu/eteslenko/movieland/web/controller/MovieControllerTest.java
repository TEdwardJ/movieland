package edu.eteslenko.movieland.web.controller;
import static org.mockito.Mockito.reset;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eteslenko.movieland.MovieTestDataGenerator;
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
    public void getAllMoviesTest() throws Exception {

        List<Movie> listMovie1 = performRequest("/v1/movies",5);
        List<Movie> listMovie2 = performRequest("/v1/movies",5);
        assertEquals(listMovie1,listMovie2);
        assertEquals(5,listMovie1.size());
        assertEquals(5,listMovie2.size());
    }

    @Test
    public void get3RandomTest() throws Exception {

        List<Movie> listMovie1 = performRequest("/v1/movie/random", 3);
        List<Movie> listMovie2 = performRequest("/v1/movie/random", 3);

        assertNotEquals(listMovie2,listMovie1);
    }

    private List<Movie> performRequest(String s, int i) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(s);

        ObjectMapper movieMapper = new ObjectMapper();

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(i)));
        MvcResult mvcResult = result.andReturn();
        String jsonMovieArray = mvcResult.getResponse().getContentAsString();
        return movieMapper.readValue(jsonMovieArray, new TypeReference<List<Movie>>() {
        });
    }

}