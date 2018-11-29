package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.AllMoviesView;

import java.util.List;
import java.util.Objects;

public class Movie {

    @JsonView(AllMoviesView.class)
    private int id;
    @JsonProperty("nameRussian")
    @JsonView(AllMoviesView.class)
    private String title;
    @JsonProperty("nameNative")
    @JsonView(AllMoviesView.class)
    private String titleInternational;

    @JsonView(AllMoviesView.class)
    @JsonProperty("yearOfRelease")
    private int releaseYear;
    @JsonIgnore
    private String description;
    @JsonView(AllMoviesView.class)
    private double rating;
    @JsonView(AllMoviesView.class)
    private double price;
    @JsonView(AllMoviesView.class)
    @JsonProperty("picturePath")
    private String picturePath;

    public String getTitleInternational() {
        return titleInternational;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setTitleInternational(String title_eng) {
        this.titleInternational = title_eng;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getId() == movie.getId() &&
                Double.compare(movie.getRating(), getRating()) == 0 &&
                Double.compare(movie.getPrice(), getPrice()) == 0 &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getTitleInternational(), movie.getTitleInternational()) &&
                Objects.equals(getDescription(), movie.getDescription()) &&
                Objects.equals(getPicturePath(), movie.getPicturePath());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getTitleInternational(), getReleaseYear(), getDescription(), getRating(), getPrice(), getPicturePath());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_eng='" + titleInternational + '\'' +
                ", price=" + price +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", picturePath=" + picturePath +
                '}';
    }
}
