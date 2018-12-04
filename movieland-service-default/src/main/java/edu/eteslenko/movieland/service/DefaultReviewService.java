package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.ReviewDao;
import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.MovieReview;
import edu.eteslenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultReviewService implements ReviewService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ReviewDao reviewDao;
    private UserDao userDao;

    @Override
    public List<MovieReview> getMovieReviews(int id) {
        List<MovieReview> movieReview = reviewDao.getByMovieId(id);
        enrich(movieReview);
        return movieReview;
    }

    private void enrich(List<MovieReview> reviewList) {
        List<Integer> idList = reviewList.stream().map(t->t.getUserId()).distinct().collect(Collectors.toList());
        Map<Integer, User> map = userDao.getById(idList);
        for (MovieReview movieReview : reviewList) {
            User user = map.get(movieReview.getUserId());
            movieReview.setUser(user);
           logger.debug("Get user {} for id {}",user, movieReview.getUserId());
        }
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
