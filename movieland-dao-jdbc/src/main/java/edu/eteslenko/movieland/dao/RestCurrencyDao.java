package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RestCurrencyDao implements CurrencyDao {

    @Autowired
    private RestTemplate restTemplate;
    private static final String RATE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final URI rateUri;
    private volatile List<Currency> currencyList;

    public RestCurrencyDao() throws URISyntaxException {
        this.rateUri = new URI(RATE_URL);;
    }

    @Override
    public List<Currency> getAll() {
        checkIfStale();
        return new ArrayList<>(this.currencyList);
    }

    private void checkIfStale() {
        Date today = new Date();
        if (today.before(currencyList.get(0).getExchangeDate())){
            refresh();
        }
    }

    @PostConstruct
    protected void refresh(){
        ResponseEntity<List<Currency>> response = restTemplate.exchange(
                RATE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Currency>>(){});
        List<Currency> currencies = response.getBody();

        this.currencyList = currencies;
    }
}
