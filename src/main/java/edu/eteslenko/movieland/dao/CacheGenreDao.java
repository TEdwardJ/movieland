package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
@Primary
public class CacheGenreDao implements GenreDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ReentrantReadWriteLock refreshLock = new ReentrantReadWriteLock();
    private GenreDao genreDao;
    private List<Genre> genreList;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> localGenreList;

        ReentrantReadWriteLock.ReadLock readLock = refreshLock.readLock();
        logger.debug("Going to acquire read lock");
        readLock.lock();
        try {
            localGenreList = new ArrayList(genreList);
            return localGenreList;
        }finally{
            readLock.unlock();
            logger.debug("Read unlock");
        }
    }

    @Scheduled(fixedRateString = "${app.cacheRefreshPeriod}")
    protected void refresh() {
        ReentrantReadWriteLock.WriteLock writeLock = refreshLock.writeLock();

        logger.debug("Going to acquire write lock");
        writeLock.lock();
        try {
            List<Genre> genreList = new ArrayList(genreDao.getAll());
            this.genreList = genreList;
        } finally {
            writeLock.unlock();
            logger.debug("Refresh is completed and write unlock is done");
        }
    }
}
