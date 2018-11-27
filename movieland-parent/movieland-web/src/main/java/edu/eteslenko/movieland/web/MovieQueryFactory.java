package edu.eteslenko.movieland.web;

import edu.eteslenko.movieland.entity.MovieQuery;
import edu.eteslenko.movieland.entity.OrderType;
import edu.eteslenko.movieland.entity.SortingColumn;

import java.util.Arrays;
import java.util.Map;

public class MovieQueryFactory {

    public static MovieQuery getMovieQuery(Map<String, String> sortingParams) {

        SortingColumn sortingColumn = Arrays.stream(SortingColumn.values())
                .filter(t -> sortingParams.containsKey(t.name().toLowerCase()))
                .findFirst()
                .orElseGet(() -> SortingColumn.DEFAULT);

        if (sortingColumn == SortingColumn.DEFAULT) {
            return MovieQuery.DEFAULT;
        }

        OrderType orderType = OrderType.DEFAULT;

        if (sortingColumn == SortingColumn.RATING) {
            orderType = OrderType.DESC;
        } else {
            orderType = Arrays.stream(OrderType.values())
                    .filter(t -> sortingParams.get(sortingColumn.name().toLowerCase()).equalsIgnoreCase(t.name()))
                    .findFirst()
                    .orElseGet(() -> OrderType.DEFAULT);
        }
        if (orderType == OrderType.DEFAULT) {
            return MovieQuery.DEFAULT;
        }
        return new MovieQuery(sortingColumn, orderType);
    }
}
