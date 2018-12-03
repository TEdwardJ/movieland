package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.CountryDao;
import edu.eteslenko.movieland.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCountryService implements CountryService {

    private CountryDao countryDao;

    @Override
    public List<Country> getAllCountries() {
        return countryDao.getAll();
    }

    @Override
    public List<Country> getCountriesByMovieId(int id) {
        return countryDao.getByMovieId(id);
    }

    @Autowired
    public void setCoutnryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
