package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class RestCurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void testGetAll() {

        List<Currency> currencyList = currencyService.getAll();
        assertNotEquals(0,currencyList.size());
        System.out.println(currencyList.get(0));
    }
}