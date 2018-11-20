package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
@Primary
public class CacheGenreDao implements GenreDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ReentrantReadWriteLock refreshLock = new ReentrantReadWriteLock();
    private CountDownLatch firstRefreshLatch = new CountDownLatch(1);
    private GenreDao genreDao;
    private List<Genre> genreList;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> localGenreList;
        if (firstRefreshLatch.getCount() > 0) {
            logger.debug("Waiting for first refresh started");
            try {
                firstRefreshLatch.await();
            } catch (InterruptedException e) {
                logger.debug("Interruption error {}", e);
            }
            logger.debug("Waiting for first refresh completed");
        }
        Lock readLock = refreshLock.readLock();
        logger.debug("Going to acquire read lock");
        readLock.lock();
        localGenreList = genreList;
        readLock.unlock();
        logger.debug("Read unlock");
        return localGenreList;
    }

    @Scheduled(fixedRateString = "${app.cacheRefreshPeriod}")
    protected void refresh() {

        List<Genre> genreList = genreDao.getAll();

        Lock writeLock = refreshLock.writeLock();

        logger.debug("Going to acquire write lock");
        writeLock.lock();
        this.genreList = genreList;
        writeLock.unlock();
        logger.debug("Refresh is completed and write unlock is done");

        if (firstRefreshLatch.getCount() > 0) {
            firstRefreshLatch.countDown();
        }
    }
}
