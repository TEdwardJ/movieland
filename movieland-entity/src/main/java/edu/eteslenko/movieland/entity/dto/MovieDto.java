package edu.eteslenko.movieland.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.Country;
import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;
import edu.eteslenko.movieland.entity.MovieReview;
import edu.eteslenko.movieland.web.view.AllMoviesView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;

import java.util.List;
import java.util.Objects;

public class MovieDto {

    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    private int id;

    @JsonProperty("nameRussian")
    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    private String title;

    @JsonProperty("nameNative")
    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    private String titleInternational;

    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    @JsonProperty("yearOfRelease")
    private int releaseYear;

    @JsonView(DetailedMovieView.class)
    private String description;

    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    private double rating;

    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    private double price;

    @JsonView({AllMoviesView.class, DetailedMovieView.class})
    @JsonProperty("picturePath")
    private String picturePath;

    @JsonView({DetailedMovieView.class})
    private List<Genre> genres;

    @JsonView({DetailedMovieView.class})
    private List<Country> countries;

    @JsonView({DetailedMovieView.class})
    private List<MovieReview> reviews;

    public MovieDto() {
    }


    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.releaseYear = movie.getReleaseYear();
        this.description = movie.getDescription();
        this.picturePath = movie.getPicturePath();
        this.title = movie.getTitle();
        this.titleInternational = movie.getTitleInternational();
        this.price = movie.getPrice();
        this.rating = movie.getRating();
    }

    public String getTitleInternational() {
        return titleInternational;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setReviews(List<MovieReview> reviews) {
        this.reviews = reviews;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movie = (MovieDto) o;
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
