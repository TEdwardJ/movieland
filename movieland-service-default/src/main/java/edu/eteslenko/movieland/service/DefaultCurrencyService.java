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

    private static final Currency UAH = new Currency(980,1,"UAH");

    private RestTemplate restTemplate;

    private String ratesUrl;


    private volatile Map<String, Currency> currencyMap;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

    @Override
    public Currency getByCode(String code) {
        if("UAH".equalsIgnoreCase(code)){
            return UAH;
        }
        return currencyMap.get(code);
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

        this.currencyMap = currencies.stream().collect(Collectors.toMap(t->t.getShortName(),t->t));
    }
    @Override
    public List<Currency> getAll() {
        return new ArrayList<>(currencyMap.values());
    }

    @Value("${currency.ratesUrl}")
    public void setRatesUrl(String ratesUrl) {
        this.ratesUrl = ratesUrl;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
