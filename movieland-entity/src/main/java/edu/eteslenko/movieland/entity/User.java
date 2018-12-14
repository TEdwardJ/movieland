package edu.eteslenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.DetailedMovieView;

import java.util.Objects;

public class User {
    @JsonView(DetailedMovieView.class)
    private int id;
    @JsonView(DetailedMovieView.class)
    private String nickname;
    private String password;
    private String email;
    private String sole;

    public User() {
    }

    public User(int id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

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

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                nickname.equals(user.nickname) &&
                getEmail().equals(user.getEmail()) &&
                getSole().equals(user.getSole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), nickname, getEmail(), getSole());
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
