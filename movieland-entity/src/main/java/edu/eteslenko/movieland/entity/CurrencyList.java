package edu.eteslenko.movieland.entity;

import java.util.List;

public class CurrencyList {
    private List<Currency> currencyList;


    public CurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }
}
