package edu.eteslenko.movieland.entity;

import java.util.Objects;

public class MovieRequest {
    private SortingColumn sortingColumn;
    private OrderType orderType;
    private RequestCurrency currency;

    public MovieRequest(SortingColumn sortingColumn, OrderType orderType) {
        this.sortingColumn = sortingColumn;
        this.orderType = orderType;
        currency = RequestCurrency.UAH;
    }

    public MovieRequest(SortingColumn sortingColumn, OrderType orderType, RequestCurrency currenct) {
        this.sortingColumn = sortingColumn;
        this.orderType = orderType;
        currency = currenct;
    }

    public static MovieRequest getDefaultRequest(){
        return new MovieRequest(SortingColumn.DEFAULT, OrderType.DEFAULT, RequestCurrency.UAH);
    }

    public void setCurrency(RequestCurrency currency) {
        this.currency = currency;
    }

    public void setSortingColumn(SortingColumn sortingColumn) {
        this.sortingColumn = sortingColumn;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public SortingColumn getSortingColumn() {
        return sortingColumn;
    }

    public OrderType getOrderType() {
        return orderType;
    }


    public RequestCurrency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieRequest that = (MovieRequest) o;
        return getSortingColumn() == that.getSortingColumn() &&
                getOrderType() == that.getOrderType() &&
                getCurrency() == that.getCurrency();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSortingColumn(), getOrderType(), getCurrency());
    }

    @Override
    public String toString() {
        return "MovieRequest{" +
                "sortingColumn=" + sortingColumn +
                ", orderType=" + orderType +
                ", currency=" + currency +
                '}';
    }
}
