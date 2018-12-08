package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultCurrencyService implements CurrencyService {

    private  String defaultCurrency;

    private RestTemplate restTemplate;

    private String ratesUrl;


    private volatile Map<String, Currency> currencyMap;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

    @Override
    public double getRate(String code) {
        if(defaultCurrency.equalsIgnoreCase(code)){
            return 1;
        }
        return currencyMap.get(code).getRate();
    }

    /*
     * Курс на дату (задається у форматі YYYYMMDD, де YYYY - рік, MM - місяць, DD - день):
     *офіційний курс гривні до інших валют та банківських металів  на дату Х
     * можна отримати починаючи з 18 00 дня Х-1.
     * Тобто на 00:00 (початок дня) офіційний курс вже відомий
     *
     * */
    @PostConstruct
    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    protected void refresh(){
        ResponseEntity<List<Currency>> response = restTemplate.exchange(
                ratesUrl.replace("{date}",dateFormatter.format(new Date())),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Currency>>(){});
        List<Currency> currencies = response.getBody();
        Map<String, Currency> temp = currencies.stream().collect(Collectors.toMap(t->t.getShortName(),t->t));
        this.currencyMap = temp;
    }

    @Value("${currency.ratesUrl}")
    public void setRatesUrl(String ratesUrl) {
        this.ratesUrl = ratesUrl;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Value("${movie.defaultCurrency}")
    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
}
