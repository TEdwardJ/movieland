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
    //As application has only one thread  to write into cache, volatile is enough
    private volatile List<Genre> genreList;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> localGenreList;
        logger.debug("Going to acquire read lock");
        localGenreList = new ArrayList(genreList);
        return localGenreList;
    }

    @Scheduled(fixedRateString = "${app.cacheRefreshPeriod}",initialDelayString = "${app.cacheRefreshPeriod}")
    @PostConstruct
    protected void refresh() {
        logger.debug("Going to acquire write lock");
        List<Genre> genreList = new ArrayList(genreDao.getAll());
        this.genreList = genreList;
        logger.debug("Refresh is completed and write unlock is done. Rows "+genreList.size());
    }
}
