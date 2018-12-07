package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.CurrencyDao;
import edu.eteslenko.movieland.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCurrencyService implements CurrencyService {

    private CurrencyDao currencyDao;


    @Override
    public Currency getByCode(String code) {
        return currencyDao
                .getAll()
                .stream()
                .filter(t->t.getShortName().equalsIgnoreCase(code))
                .findFirst().get();
    }

    @Override
    public List<Currency> getAll() {
        return currencyDao.getAll();
    }

    @Autowired
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }
}
