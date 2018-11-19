package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;

    @Autowired
    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> get3RandomMovies() {
        return movieDao.getThreeRandom();
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre) {
        return movieDao.getMoviesByGenre(genre);
    }

}
