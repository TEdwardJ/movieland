package edu.eteslenko.movieland.service.security;

import edu.eteslenko.movieland.entity.User;

import java.time.LocalDateTime;

public class Session {
    private LocalDateTime startTime;
    private User user;
    private String token;

    public Session(User user, String token) {
        this.user = user;
        this.token = token;
        this.startTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
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
                "startTime=" + startTime +
                ", user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
