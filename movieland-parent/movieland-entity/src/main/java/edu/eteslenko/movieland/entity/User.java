package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;

public class User {
    @JsonView(DetailedMovieView.class)
    private int id;
    @JsonView(DetailedMovieView.class)
    private String nickname;
    private String password;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return nickname;
    }

    public void setLogin(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
