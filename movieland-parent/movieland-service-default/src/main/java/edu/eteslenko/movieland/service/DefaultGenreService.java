package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.GenreDao;
import edu.eteslenko.movieland.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultGenreService  implements GenreService{

    private GenreDao genreDao;

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

}
