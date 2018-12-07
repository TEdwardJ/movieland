package edu.eteslenko.movieland.web.controller;

import edu.eteslenko.movieland.entity.*;
import edu.eteslenko.movieland.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


@Component
public class MovieRequestFactory {

    private CurrencyService currencyService;

    public MovieRequest getMovieRequest(Map<String, String> requestParams) {
        MovieRequest request = MovieRequest.getDefaultRequest();
        if (requestParams.containsKey("currency")) {
            RequestCurrency currency = Arrays.stream(RequestCurrency.values())
                    .filter(t -> requestParams.get("currency").equalsIgnoreCase(t.name().toLowerCase()))
                    .findFirst()
                    .orElseGet(() -> RequestCurrency.UAH);
            currency.setRate(currencyService.getByCode(currency.name()).getRate());
            request.setCurrency(currency);
        }
        SortingColumn sortingColumn = Arrays.stream(SortingColumn.values())
                .filter(t -> requestParams.containsKey(t.name().toLowerCase()))
                .findFirst()
                .orElseGet(() -> SortingColumn.DEFAULT);

        if (sortingColumn != SortingColumn.DEFAULT) {
            request.setSortingColumn(sortingColumn);

            OrderType orderType = OrderType.DEFAULT;

            if (sortingColumn == SortingColumn.RATING) {
                orderType = OrderType.DESC;
            } else {
                orderType = Arrays.stream(OrderType.values())
                        .filter(t -> requestParams.get(sortingColumn.name().toLowerCase()).equalsIgnoreCase(t.name()))
                        .findFirst()
                        .orElseGet(() -> OrderType.DEFAULT);
            }
            if (orderType != OrderType.DEFAULT) {
                request.setOrderType(orderType);
            }
        }
        return request;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
}
