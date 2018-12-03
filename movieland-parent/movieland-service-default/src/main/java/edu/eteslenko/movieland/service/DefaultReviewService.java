package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.ReviewDao;
import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.MovieReview;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultReviewService implements ReviewService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ReviewDao reviewDao;
    private UserDao userDao;

    @Override
    public List<MovieReview> getMovieReviews(int id) {
        return reviewDao
                .getByMovieId(id)
                .parallelStream()
                .peek(t->enrich(t))
                .collect(Collectors.toList());
    }

    private MovieReview enrich(MovieReview review) {
        logger.debug("Get user for id {}",review.getUserId());
        review.setUser(userDao.getById(review.getUserId()));
        return review;
    }

    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
