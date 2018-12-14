package edu.eteslenko.movieland.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.entity.dto.SessionUserDto;
import edu.eteslenko.movieland.service.SecurityService;
import edu.eteslenko.movieland.web.view.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@RestController
public class UserController {

    private SecurityService securityService;


    @JsonView(LoginResponse.class)
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SessionUserDto> login(@RequestBody User user){
        SessionUserDto userDto = securityService.auth(user);
        HttpStatus responseStatus = userDto==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>(userDto, responseStatus);

    }

    @DeleteMapping(path = "/logout")
    public void logout(@RequestHeader String token){
        securityService.logout(token);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
