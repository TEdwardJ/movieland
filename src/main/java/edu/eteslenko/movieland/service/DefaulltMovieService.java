package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaulltMovieService implements MovieService {
    @Autowired
    private MovieDao movieDao;
    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> get3RandomMovies() {
        return movieDao.get3Random();
    }

}
