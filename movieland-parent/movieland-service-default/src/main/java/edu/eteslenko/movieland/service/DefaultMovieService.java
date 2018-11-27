package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;

    @Override
    public List<Movie> getAllMovies(MovieQuery movieQuery) {
        return movieDao.getAll(movieQuery);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAll(MovieQuery.DEFAULT);
    }

    @Override
    public List<Movie> getThreeRandomMovies(MovieQuery movieQuery) {
        return movieDao.getThreeRandom();
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        return getThreeRandomMovies(MovieQuery.DEFAULT);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre) {
        return getMoviesByGenre(genre, MovieQuery.DEFAULT);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genre, MovieQuery movieQuery) {
        return movieDao.getMoviesByGenre(genre, movieQuery);
    }


    @Autowired
    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }


}
