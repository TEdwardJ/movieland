package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Country;

import java.util.List;

public interface CountryDao  {

    List<Country> getAll();

    List<Country> getByMovieId(int id);

}
