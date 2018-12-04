package edu.eteslenko.movieland.web.controller;

import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.service.GenreService;
import edu.eteslenko.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GenreService genreService;

    @GetMapping(path = "/genre", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
}
