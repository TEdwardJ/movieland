package edu.eteslenko.movieland.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import edu.eteslenko.movieland.dao.JdbcUserDao;
import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.entity.dto.SessionUserDto;
import edu.eteslenko.movieland.service.SecurityService;
import edu.eteslenko.movieland.web.view.LoginResponse;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static edu.eteslenko.movieland.service.security.DefaultSecurityService.md5Apache;

@RestController
public class UserController {

    private SecurityService securityService;
    @Autowired
    private JdbcUserDao userDao;

    private static Random r = new Random();
    private static RandomStringGenerator rs = new RandomStringGenerator
            .Builder()
            .withinRange('a', 'z')
            .usingRandom(r::nextInt)
            .build();


    public void main() {

        List<User> users = userDao.getAll();

        for (User user : users) {
            encrypt(user);
            user.setPassword(md5Apache(user.getPassword() + user.getSole()));
            userDao.update(user);
        }
    }

    @JsonView(LoginResponse.class)
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SessionUserDto> login(@RequestBody User user){
        SessionUserDto userDto = securityService.auth(user);
        HttpStatus responseStatus = userDto==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>(userDto, responseStatus);

    }

    private static void encrypt(User user) {
        String sole = rs.generate(10);
        user.setSole(sole);
    }


    @GetMapping("/users/encrypt")
    public void encript(){
        main();
    }


    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
