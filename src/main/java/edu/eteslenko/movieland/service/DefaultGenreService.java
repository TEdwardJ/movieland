package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.GenreDao;
import edu.eteslenko.movieland.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultGenreService  implements GenreService{

    private GenreDao jdbcGenreDao;

    @Autowired
    public DefaultGenreService(GenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }

    @Override
    public List<Genre> getAllGenres() {
        return jdbcGenreDao.getAll();
    }
}
