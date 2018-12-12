package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.MovieRequest;
import edu.eteslenko.movieland.entity.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAllMovies(MovieRequest movieRequest);

    List<MovieDto> getAllMovies();

    List<MovieDto> getThreeRandomMovies(MovieRequest movieRequest);

    List<MovieDto> getThreeRandomMovies();

    List<MovieDto> getMoviesByGenre(int genre);

    List<MovieDto> getMoviesByGenre(int genre, MovieRequest movieRequest);

    MovieDto getById(int id);
    MovieDto getById(int id, MovieRequest movieRequest);
}
