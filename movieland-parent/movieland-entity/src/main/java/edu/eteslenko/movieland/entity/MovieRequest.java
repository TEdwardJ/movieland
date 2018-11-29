package edu.eteslenko.movieland.entity;

import java.util.Objects;

public class MovieRequest {
    public static final MovieRequest DEFAULT = new MovieRequest(SortingColumn.DEFAULT,OrderType.DEFAULT);
    private SortingColumn sortingColumn;
    private OrderType orderType;

    public MovieRequest(SortingColumn sortingColumn, OrderType orderType) {
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
        MovieRequest that = (MovieRequest) o;
        return getSortingColumn() == that.getSortingColumn() &&
                getOrderType() == that.getOrderType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSortingColumn(), getOrderType());
    }

    @Override
    public String toString() {
        return "MovieRequest{" +
                "sortingColumn=" + sortingColumn +
                ", orderType=" + orderType +
                '}';
    }
}
