package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
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
    public List<Movie> getAllMovies(MovieRequest movieRequest) {
        return movieDao.getAll(movieRequest);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAll(MovieRequest.DEFAULT);
    }

    @Override
    public List<Movie> getThreeRandomMovies(MovieRequest movieRequest) {
        return movieDao.getThreeRandom();
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        return getThreeRandomMovies(MovieRequest.DEFAULT);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre) {
        return getMoviesByGenre(genre, MovieRequest.DEFAULT);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre, MovieRequest movieRequest) {
        return movieDao.getMoviesByGenre(genre, movieRequest);
    }

}
