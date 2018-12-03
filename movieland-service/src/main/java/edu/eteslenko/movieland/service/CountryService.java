package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    List<Country> getCountriesByMovieId(int id);
}
