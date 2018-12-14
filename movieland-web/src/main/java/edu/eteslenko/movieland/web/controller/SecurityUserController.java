package edu.eteslenko.movieland.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.entity.dto.SessionUserDto;
import edu.eteslenko.movieland.service.SecurityService;
import edu.eteslenko.movieland.web.view.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;


@RestController
public class SecurityUserController {


    private Logger logger = LoggerFactory.getLogger(getClass());
    private SecurityService securityService;


    @JsonView(LoginResponse.class)
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SessionUserDto> login(@RequestBody User user) {
        Optional<SessionUserDto> userDto = Optional.ofNullable(securityService.auth(user));
        if (userDto.isPresent()) {
            logger.info("Successful signing up for user {}",user.getEmail());
            return new ResponseEntity<>(userDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(path = "/logout")
    public void logout(@RequestHeader String token) {
        securityService.logout(token);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
