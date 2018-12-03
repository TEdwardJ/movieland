package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.MovieReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReviewDao implements ReviewDao {

    private static final ReviewRowMapper ROW_MAPPER = new ReviewRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    private String reviewSelectByMovieIdQuery;

    @Override
    public List<MovieReview> getByMovieId(int id) {
        logger.debug("Getting all reviews for Movie {} from DB", id);
        List<MovieReview> list =
                jdbcTemplate.query(reviewSelectByMovieIdQuery, ROW_MAPPER, id);

        return list;
    }

    @Value("${query.reviewByMovieId}")
    public void setReviewSelectByMovieIdQuery(String reviewSelectByMovieIdQuery) {
        this.reviewSelectByMovieIdQuery = reviewSelectByMovieIdQuery;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
