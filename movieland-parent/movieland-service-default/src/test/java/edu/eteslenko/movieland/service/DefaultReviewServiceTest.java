package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.ReviewDao;
import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.MovieReview;
import edu.eteslenko.movieland.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class DefaultReviewServiceTest {
    private List<MovieReview> expectedReviews;

    @Mock
    private ReviewDao jdbcUserDao;

    @Autowired
    @InjectMocks
    private ReviewService reviewService;

    @Before
    public void init(){
        if (expectedReviews==null) {
            expectedReviews = reviewService.getMovieReviews(1);
        }
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getMovieReviews() {
        Mockito.when(reviewService.getMovieReviews(anyInt())).thenReturn(expectedReviews);
        List<MovieReview> actualReviews = reviewService.getMovieReviews(8);
        Assert.assertEquals(expectedReviews,actualReviews);
    }
}