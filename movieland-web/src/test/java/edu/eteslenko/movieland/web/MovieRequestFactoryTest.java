package edu.eteslenko.movieland.web;

import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.OrderType;
import edu.eteslenko.movieland.entity.SortingColumn;
import edu.eteslenko.movieland.web.controller.MovieRequestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/MovieDispatcher-servlet.xml",
        "classpath:testContextWeb.xml"})
@WebAppConfiguration
public class MovieRequestFactoryTest {

    @Autowired
    MovieRequestFactory queryFactory;

    private MovieRequest getMovieRequest(Map<String, String> requestParams){
        return queryFactory.getMovieRequest(requestParams);
    }

    @Test
    public void testGetMovieRequest(){
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("rating","desc");
        MovieRequest movieRequest1 = getMovieRequest(requestMap);
        assertEquals(new MovieRequest(SortingColumn.RATING,OrderType.DESC), movieRequest1);

        requestMap.put("rating","asc");
        MovieRequest movieRequest2 = getMovieRequest(requestMap);
        assertEquals(new MovieRequest(SortingColumn.RATING,OrderType.DESC), movieRequest2);

        requestMap.clear();
        MovieRequest movieRequest3 = getMovieRequest(requestMap);
        assertEquals(MovieRequest.getDefaultRequest(), movieRequest3);

        requestMap.put("aaa","bbbdesc");
        MovieRequest movieRequest4 = getMovieRequest(requestMap);
        assertEquals(MovieRequest.getDefaultRequest(), movieRequest4);
    }
}