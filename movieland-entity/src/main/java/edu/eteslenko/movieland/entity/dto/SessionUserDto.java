package edu.eteslenko.movieland.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.web.view.LoginResponse;

public class SessionUserDto {
    @JsonView(LoginResponse.class)
    private String uuid;
    @JsonView(LoginResponse.class)
    private String nickname;

    public SessionUserDto(String uuid, String nickname) {
        this.uuid = uuid;
        this.nickname = nickname;
    }
}
