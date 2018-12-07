package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Currency;

import java.util.List;

public interface CurrencyService {


    Currency getByCode(String code);

    List<Currency> getAll();


}
