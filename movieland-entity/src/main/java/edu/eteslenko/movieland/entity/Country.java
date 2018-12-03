package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;

import java.util.Objects;

public class Country {
    @JsonView({DetailedMovieView.class})
    private int id;
    @JsonView({DetailedMovieView.class})
    private String name;

    public Country(int id, String name) {
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
        Country country = (Country) o;
        return getId() == country.getId() &&
                Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
