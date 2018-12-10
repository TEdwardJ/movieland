package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.entity.dto.SessionUserDto;

public interface SecurityService {

    SessionUserDto auth(User user);

    void logout(String token);
}
