package edu.eteslenko.movieland.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eteslenko.movieland.entity.*;
import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
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
    public void testGetMovieByGenre() throws Exception {
        List<MovieDto> listMovie1 = performMovieRequest("/v1/movie/genre/2", 3);
        List<MovieDto> listMovie2 = performMovieRequest("/v1/movie/genre/2", 3);
        assertEquals(listMovie1, listMovie2);
        assertEquals(3, listMovie1.size());
        assertEquals(3, listMovie2.size());

        List<MovieDto> listMovie3 = performMovieRequest("/v1/movie/genre/3", 2);
        List<MovieDto> listMovie4 = performMovieRequest("/v1/movie/genre/4", 1);
        assertEquals(2, listMovie3.size());
        assertEquals(1, listMovie4.size());

        List<MovieDto> listMovie5 = performMovieRequest("/v1/movie/genre/111", 0);
        assertEquals(0, listMovie5.size());
    }


    @Test
    public void testGetAllGenres() throws Exception {
        List<Genre> listGenre1 = performGenreRequest("/v1/genre", 15);
        List<Genre> listGenre2 = performGenreRequest("/v1/genre", 15);
        assertEquals(listGenre1, listGenre2);
        assertEquals(15, listGenre1.size());
        assertEquals(15, listGenre2.size());
    }

    @Test
    public void testGetAllMovies() throws Exception {
        List<MovieDto> listMovie1 = performMovieRequest("/v1/movies", 5);
        List<MovieDto> listMovie2 = performMovieRequest("/v1/movies", 5);
        assertEquals(listMovie1, listMovie2);
        assertEquals(5, listMovie1.size());
        assertEquals(5, listMovie2.size());
    }

    @Test
    public void testGetAllMoviesWithSorting() throws Exception {
        List<MovieDto> listMovie1 = performMovieRequest("/v1/movies?rating=desc", 5);
        assertEquals(5, listMovie1.size());
        assertEquals(8.9, listMovie1.get(0).getRating(),0);
        List<MovieDto> listMovie2 = performMovieRequest("/v1/movies?rating=asc", 5);
        assertEquals(8.9, listMovie2.get(0).getRating(),0);
    }

    @Test
    public void testGetTreeRandom() throws Exception {

        List<MovieDto> listMovie1 = performMovieRequest("/v1/movie/random", 3);
        List<MovieDto> listMovie2 = performMovieRequest("/v1/movie/random", 3);

        assertNotEquals(listMovie2, listMovie1);
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

    private List<MovieDto> performMovieRequest(String s, int i) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(s);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(i)));
        MvcResult mvcResult = result.andReturn();
        String jsonMovieArray = mvcResult.getResponse().getContentAsString();
        return jsonMapper.readValue(jsonMovieArray, new TypeReference<List<MovieDto>>() {
        });
    }

    private void performMovieDtoRequest(String s, int i) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(s);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", equalTo(i)))
                .andExpect(jsonPath("$.genres", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.countries", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.reviews", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.description", not(empty())))
                .andExpect(jsonPath("$.nameRussian", not(empty())))
                .andExpect(jsonPath("$.nameNative", not(empty())))
                .andExpect(jsonPath("$.yearOfRelease", not(empty())))
        ;
        MvcResult mvcResult = result.andReturn();
        String jsonMovieArray = mvcResult.getResponse().getContentAsString();

    }

    @Test
    public void testGetMoviesByGenreWithSorting() throws Exception {
        List<MovieDto> listMovie1 = performMovieRequest("/v1/movie/genre/2?rating=desc", 3);
        assertEquals(3, listMovie1.size());
        assertEquals(8.9, listMovie1.get(0).getRating(),0);
        List<MovieDto> listMovie2 = performMovieRequest("/v1/movie/genre/2?price=desc", 3);
        assertEquals(145.9, listMovie2.get(0).getPrice(),0);
    }

    @Test
    public void testGetMovieById() throws Exception {
        performMovieDtoRequest("/v1/movie/1", 1);
        performMovieDtoRequest("/v1/movie/2", 2);
    }
}
