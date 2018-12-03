package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEnrichService extends AbstractEnrichService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;


    @Override
    void enrich(MovieDto movie) {
        int movieId = movie.getId();
        logger.debug("Enriching movie with id {}", movieId);
        enrichMovie(movieId, genreService::getGenresByMovieId, movie::setGenres);
        enrichMovie(movieId, countryService::getCountriesByMovieId, movie::setCountries);
        enrichMovie(movieId, reviewService::getMovieReviews, movie::setReviews);

        logger.debug("Movie {} has been enriched", movieId);
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


}
