package edu.eteslenko.movieland.web;

import edu.eteslenko.movieland.entity.MovieQuery;
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
        MovieQuery movieQuery1 = getMovieQuery(requestMap);
        assertEquals(new MovieQuery(SortingColumn.RATING,OrderType.DESC), movieQuery1);

        requestMap.put("rating","asc");
        MovieQuery movieQuery2 = getMovieQuery(requestMap);
        assertEquals(new MovieQuery(SortingColumn.RATING,OrderType.DESC), movieQuery2);

        requestMap.clear();
        MovieQuery movieQuery3 = getMovieQuery(requestMap);
        assertEquals(MovieQuery.DEFAULT, movieQuery3);

        requestMap.put("aaa","bbbdesc");
        MovieQuery movieQuery4 = getMovieQuery(requestMap);
        assertEquals(MovieQuery.DEFAULT, movieQuery4);
    }
}