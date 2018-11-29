package edu.eteslenko.movieland.web;

import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.OrderType;
import edu.eteslenko.movieland.entity.SortingColumn;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static edu.eteslenko.movieland.web.MovieQueryFactory.getMovieQuery;
import static org.junit.Assert.*;

public class MovieQueryFactoryTest {

    @Test
    public void testGetMovieQuery(){
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("rating","desc");
        MovieRequest movieRequest1 = getMovieQuery(requestMap);
        assertEquals(new MovieRequest(SortingColumn.RATING,OrderType.DESC), movieRequest1);

        requestMap.put("rating","asc");
        MovieRequest movieRequest2 = getMovieQuery(requestMap);
        assertEquals(new MovieRequest(SortingColumn.RATING,OrderType.DESC), movieRequest2);

        requestMap.clear();
        MovieRequest movieRequest3 = getMovieQuery(requestMap);
        assertEquals(MovieRequest.DEFAULT, movieRequest3);

        requestMap.put("aaa","bbbdesc");
        MovieRequest movieRequest4 = getMovieQuery(requestMap);
        assertEquals(MovieRequest.DEFAULT, movieRequest4);
    }
}