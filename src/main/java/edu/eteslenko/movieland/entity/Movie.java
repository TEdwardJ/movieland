package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.AllMovieView;

import java.util.List;
import java.util.Objects;

public class Movie {

    @JsonView(AllMovieView.class)
    private int id;
    @JsonProperty("nameRussian")
    @JsonView(AllMovieView.class)
    private String title;
    @JsonProperty("nameNative")
    @JsonView(AllMovieView.class)
    private String titleInternational;

    @JsonView(AllMovieView.class)
    @JsonProperty("yearOfRelease")
    private int releaseYear;
    private List<String> countryProducer;
    @JsonIgnore
    private String description;
    @JsonView(AllMovieView.class)
    private double rating;
    @JsonView(AllMovieView.class)
    private double price;
    @JsonView(AllMovieView.class)
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

    public List<String> getCountryProducer() {
        return countryProducer;
    }

    public void setCountryProducer(List<String> countryProducer) {
        this.countryProducer = countryProducer;
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
                getReleaseYear() == movie.getReleaseYear() &&
                Double.compare(movie.getRating(), getRating()) == 0 &&
                Double.compare(movie.getPrice(), getPrice()) == 0 &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getTitleInternational(), movie.getTitleInternational()) &&
                Objects.equals(getCountryProducer(), movie.getCountryProducer()) &&
                Objects.equals(getDescription(), movie.getDescription()) &&
                Objects.equals(getPicturePath(), movie.getPicturePath());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getTitleInternational(), getReleaseYear(), getCountryProducer(), getDescription(), getRating(), getPrice(), getPicturePath());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_eng='" + titleInternational + '\'' +
                ", price=" + price +
                ", releaseYear=" + releaseYear +
                ", countryProducer=" + countryProducer +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", picturePath=" + picturePath +
                '}';
    }
}
