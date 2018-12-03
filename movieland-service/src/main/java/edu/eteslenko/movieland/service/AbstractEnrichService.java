package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.dto.MovieDto;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractEnrichService {
    abstract void enrich(MovieDto movie);

    protected <T> boolean enrichMovie(Integer id, Function<Integer, T> service, Consumer<T> enricher) {
        T data = service.apply(id);
        enricher.accept(data);
        return true;
    }
}
