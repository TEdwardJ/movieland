package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.RequestCurrency;
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
    private CurrencyService currencyService;

    private AbstractEnrichService enrichService;

    protected List<MovieDto> convertToDto(List<Movie> movieList) {
        return convertToDto(movieList, RequestCurrency.UAH);
    }

    protected MovieDto convertToDto(Movie movie, double currentRate) {
        MovieDto movieDto = new MovieDto(movie);
        movieDto.setPrice(movieDto.getPrice()/currentRate);
        return movieDto;
    }

    protected List<MovieDto> convertToDto(List<Movie> movieList, RequestCurrency currency) {
        double currentRate = currencyService.getRate(currency.name());
        return movieList
                .stream()
                .map(t->convertToDto(t,currentRate))
                .collect(Collectors.toList());
    }

    @Autowired
    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }


    @Override
    public List<MovieDto> getAllMovies(MovieRequest movieRequest) {
        return convertToDto(movieDao.getAll(movieRequest), movieRequest.getCurrency());
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return getAllMovies(MovieRequest.getDefaultRequest());
    }

    @Override
    public List<MovieDto> getThreeRandomMovies(MovieRequest movieRequest) {
        return convertToDto(movieDao.getThreeRandom(), movieRequest.getCurrency());
    }

    @Override
    public List<MovieDto> getThreeRandomMovies() {
        return getThreeRandomMovies(MovieRequest.getDefaultRequest());
    }

    @Override
    public List<MovieDto> getMoviesByGenre(int genre) {
        return getMoviesByGenre(genre, MovieRequest.getDefaultRequest());
    }

    @Override
    public List<MovieDto> getMoviesByGenre(int genre, MovieRequest movieRequest) {
        return convertToDto(movieDao.getMoviesByGenre(genre, movieRequest), movieRequest.getCurrency());
    }

    @Override
    public MovieDto getById(int id) {
        return getById(id, MovieRequest.getDefaultRequest());
    }

    public MovieDto getById(int id, MovieRequest request) {
        Movie movie = movieDao.getById(id);
        if (movie == null) {
            return null;
        }
        MovieDto movieDto = convertToDto(movie, currencyService.getRate(request.getCurrency().name()));
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

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
}
