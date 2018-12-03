package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;

import java.time.LocalDateTime;

public class MovieReview {

    private int id;
    private int userId;
    @JsonView(DetailedMovieView.class)
    private User user;
    @JsonView(DetailedMovieView.class)
    private String text;
    private int movieId;
    private LocalDateTime reviewDate;


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public int getMovieId() {
        return movieId;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", movieId=" + movieId +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
