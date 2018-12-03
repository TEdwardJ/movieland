package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultMovieService implements MovieService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private MovieDao movieDao;

    private AbstractEnrichService enrichService;


    protected List<MovieDto> convertToDto(List<Movie> movieList) {
        return movieList
                .stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }

    @Autowired
    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }


    @Override
    public List<MovieDto> getAllMovies(MovieRequest movieRequest) {
        return convertToDto(movieDao.getAll(movieRequest));
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return getAllMovies(MovieRequest.DEFAULT);
    }

    @Override
    public List<MovieDto> getThreeRandomMovies(MovieRequest movieRequest) {
        return convertToDto(movieDao.getThreeRandom());
    }

    @Override
    public List<MovieDto> getThreeRandomMovies() {
        return getThreeRandomMovies(MovieRequest.DEFAULT);
    }

    @Override
    public List<MovieDto> getMoviesByGenre(int genre) {
        return getMoviesByGenre(genre, MovieRequest.DEFAULT);
    }

    @Override
    public List<MovieDto> getMoviesByGenre(int genre, MovieRequest movieRequest) {
        return convertToDto(movieDao.getMoviesByGenre(genre, movieRequest));
    }

    @Override
    public MovieDto getById(int id) {
        Movie movie = movieDao.getById(id);
        if (movie == null) {
            return null;
        }
        MovieDto movieDto = new MovieDto(movie);
        enrichService.enrich(movieDto);
        return movieDto;
    }

    protected void enrich(MovieDto movie) {
        enrichService.enrich(movie);
    }

    @Autowired
    public void setEnrichService(AbstractEnrichService enrichService) {
        this.enrichService = enrichService;
    }
}
