package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Service
@Primary
public class ParallelEnrichService extends AbstractEnrichService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;

    private ExecutorService enrichExecutor = Executors.newCachedThreadPool();

    private Integer parallelServiceTimout;

    protected void enrich(MovieDto movie) {
        int movieId = movie.getId();
        logger.debug("Enriching movie with id {}", movieId);
        List<Callable<Boolean>> taskList = new ArrayList<>();
        taskList.add(() -> enrichMovie(movieId, genreService::getGenresByMovieId, movie::setGenres));
        taskList.add(() -> enrichMovie(movieId, countryService::getCountriesByMovieId, movie::setCountries));
        taskList.add(() -> enrichMovie(movieId, reviewService::getMovieReviews, movie::setReviews));
        try {
            List<Future<Boolean>> futureList = enrichExecutor.invokeAll(taskList, parallelServiceTimout, TimeUnit.SECONDS);
            int successCount = (int)futureList.stream().filter(t->!t.isCancelled()).count();
            int failedCount = (int)futureList.stream().filter(t->t.isCancelled()).count();

            logger.debug("Movie {} has been enriched {}, success threads {}, failedThreads {}", movieId, successCount, failedCount);
        } catch (InterruptedException e) {
            logger.debug("Movie service is interrupted");
        }
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

    @Value("${movie.parallelServiceTimout}")
    public void setParallelServiceTimout(Integer parallelServiceTimout) {
        this.parallelServiceTimout = parallelServiceTimout;
    }
}
