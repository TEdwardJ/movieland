package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.MovieDao;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class DefaultMovieService implements MovieService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private MovieDao movieDao;
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;
    private ExecutorService enrichExecutor = Executors.newCachedThreadPool();
    private Integer parallelServiceDelay;

    class EnrichTask implements Callable<Boolean> {
        private String description;
        private Supplier<Boolean> call;
        private CountDownLatch latch;

        public EnrichTask(String description, CountDownLatch latch, Supplier<Boolean> call) {
            this.description = description;
            this.latch = latch;
            this.call = call;
        }

        @Override
        public Boolean call() throws Exception {
                if (!Thread.currentThread().isInterrupted()) {
                    Boolean result = call.get();
                    latch.countDown();
                    return result;
                }
            return false;
        }
    }


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
        enrich(movieDto);
        return movieDto;
    }

    private <T> boolean enrichMovie(Integer id, Function<Integer, T> service, Consumer<T> enricher) {
        Function<Integer, T> service1 = service;
        T data = service1.apply(id);
        if (data != null) {
            enricher.accept(data);
            return true;
        }
        return false;
    }

    protected void enrich(MovieDto movie) {
        CountDownLatch latch = new CountDownLatch(3);
        int movieId = movie.getId();
        logger.debug("Enriching movie with id {}", movieId);
        List<Future<Boolean>> taskList = new ArrayList<>();
        taskList.add(enrichExecutor.submit(new EnrichTask("Genre enriching", latch, () -> enrichMovie(movieId, genreService::getGenresByMovieId, movie::setGenres))));
        taskList.add(enrichExecutor.submit(new EnrichTask("Countries enriching", latch, () -> enrichMovie(movieId, countryService::getCountriesByMovieId, movie::setCountries))));
        taskList.add(enrichExecutor.submit(new EnrichTask("Review enriching", latch, () -> enrichMovie(movieId, reviewService::getMovieReviews, movie::setReviews))));
        try {
            latch.await(parallelServiceDelay, TimeUnit.SECONDS);
            boolean totalResult = true;
            for (Future future : taskList) {
                if (future.isDone()) {
                    try {
                        boolean enrichResult = (boolean) future.get();
                        if (!enrichResult) {
                            totalResult = enrichResult;
                        }
                        logger.debug("Enricher returned value {}", enrichResult);
                    } catch (InterruptedException e) {
                        logger.debug("Enriching Service was interrupted {}", e);
                    } catch (ExecutionException e) {
                        logger.debug("Enricher execution threw exeption {}", e);
                    }
                } else {
                    logger.debug("Enricher will be cancelled");
                    future.cancel(true);
                    totalResult = false;
                }
            }
            logger.debug("Movie {} has been enriched {}", movieId, totalResult ? "completely" : "partially");
        } catch (InterruptedException e) {
            e.printStackTrace();
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
    @Value("${movie.parallelServiceDelay}")
    public void setParallelServiceDelay(Integer parallelServiceDelay) {
        this.parallelServiceDelay = parallelServiceDelay;
    }
}
