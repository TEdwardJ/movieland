package edu.eteslenko.movieland.entity;

import java.util.Objects;

public class MovieQuery {
    public static final MovieQuery DEFAULT = new MovieQuery(SortingColumn.DEFAULT,OrderType.DEFAULT);
    SortingColumn sortingColumn;
    OrderType orderType;

    public MovieQuery(SortingColumn sortingColumn, OrderType orderType) {
        this.sortingColumn = sortingColumn;
        this.orderType = orderType;
    }

    public SortingColumn getSortingColumn() {
        return sortingColumn;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieQuery that = (MovieQuery) o;
        return getSortingColumn() == that.getSortingColumn() &&
                getOrderType() == that.getOrderType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSortingColumn(), getOrderType());
    }

    @Override
    public String toString() {
        return "MovieQuery{" +
                "sortingColumn=" + sortingColumn +
                ", orderType=" + orderType +
                '}';
    }
}
