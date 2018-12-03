package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
@Primary
public class CacheGenreDao implements GenreDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ReentrantReadWriteLock refreshLock = new ReentrantReadWriteLock();
    private GenreDao genreDao;
    //As the application has only one thread to change cache collection link, volatile is enough
    private volatile List<Genre> genreList;

    @Override
    public List<Genre> getAll() {
        logger.debug("Going to read cache");
        List<Genre> localGenreList = new ArrayList(genreList);;
        return localGenreList;
    }

    @Override
    public List<Genre> getByMovieId(int id) {
        return genreDao.getByMovieId(id);
    }

    @Scheduled(fixedRateString = "${app.cacheRefreshPeriod}", initialDelayString = "${app.cacheRefreshPeriod}")
    @PostConstruct
    protected void refresh() {
        logger.debug("Going to update cache collection link");
        List<Genre> genreList = new ArrayList<>(genreDao.getAll());
        this.genreList = genreList;
        logger.debug("Refresh is completed. Rows {}", genreList.size());
    }

   @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
}
