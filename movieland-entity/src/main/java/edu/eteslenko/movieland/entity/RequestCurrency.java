package edu.eteslenko.movieland.entity;

public enum RequestCurrency {
    UAH(1),
    USD,
    EURO;

    private double rate;

    RequestCurrency() {
    }

    RequestCurrency(double rate) {
        this.rate = rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
