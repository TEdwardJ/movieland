package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class RestCurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void testGetAll() {

        double uahCurrencyRate = currencyService.getRate("UAH");
        assertEquals(1,uahCurrencyRate,0);

        double usdCurrencyRate = currencyService.getRate("USD");
        assertTrue(usdCurrencyRate>25);
    }
}