package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;
@JsonIgnoreProperties
public class Currency {
    @JsonProperty("r030")
    private int isoCode;
    @JsonProperty("txt")
    private String name;
    @JsonProperty("rate")
    private double rate;
    @JsonProperty("cc")
    private String shortName;
    @JsonProperty("exchangedate")
    private Date exchangeDate;

    public Currency() {
    }

    public Currency(int isoCode, double rate, String shortName) {
        this.isoCode = isoCode;
        this.rate = rate;
        this.shortName = shortName;
    }

    @JsonIgnore
    public int getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getShortName() {
        return shortName;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return getIsoCode() == currency.getIsoCode() &&
                name.equals(currency.name) &&
                shortName.equals(currency.shortName) &&
                exchangeDate.equals(currency.exchangeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsoCode(), name, shortName, exchangeDate);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "isoCode=" + isoCode +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", shortName='" + shortName + '\'' +
                ", excahngeDate=" + exchangeDate +
                '}';
    }
}
