package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.MovieReview;
import edu.eteslenko.movieland.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class JdbcReviewDaoTest {

    private List<MovieReview> expectedReviews;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @InjectMocks
    ReviewDao jdbcReviewDao;
    @Before
    public void init(){
        expectedReviews = jdbcReviewDao.getByMovieId(1);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getByMovieId() {

        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(RowMapper.class),anyInt())).thenReturn(expectedReviews);
        List<MovieReview> actualReviews = jdbcReviewDao.getByMovieId(8);
        Assert.assertEquals(expectedReviews,actualReviews);
        Assert.assertNotEquals(0, actualReviews.size());
    }
}