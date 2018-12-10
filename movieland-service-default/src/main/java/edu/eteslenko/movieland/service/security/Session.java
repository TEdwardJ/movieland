package edu.eteslenko.movieland.service.security;

import edu.eteslenko.movieland.entity.User;

import java.time.LocalDateTime;

public class Session {
    private LocalDateTime expiredDate;
    private User user;
    private String token;

    public Session(User user, String token) {
        this.user = user;
        this.token = token;
        this.expiredDate = LocalDateTime.now();
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Session{" +
                "expiredDate=" + expiredDate +
                ", user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
