package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;

public interface UserDao {
    User getById(int id);
}
