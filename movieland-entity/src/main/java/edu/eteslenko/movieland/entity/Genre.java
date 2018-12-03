package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;

import java.util.Objects;

public class Genre {
    @JsonView({DetailedMovieView.class})
    private int id;
    @JsonView({DetailedMovieView.class})
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
