package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User getById(int id);

    User getOne(String email);

    Map<Integer, User> getById(List<Integer> id);

    User checkPassword(String enteredPassword);
}
