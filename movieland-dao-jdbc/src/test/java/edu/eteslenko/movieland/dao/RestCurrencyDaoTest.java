package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class RestCurrencyDaoTest {

    @Autowired
    private CurrencyDao currencyDao;

    @Test
    public void testGetAll() {

        List<Currency> currencyList = currencyDao.getAll();
        assertNotEquals(0,currencyList.size());
        System.out.println(currencyList.get(0));
    }
}