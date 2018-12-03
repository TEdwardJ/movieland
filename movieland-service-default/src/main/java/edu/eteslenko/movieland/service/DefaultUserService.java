package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    UserDao userDao;

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
