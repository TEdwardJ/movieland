package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("usr_id"));
        user.setEmail(resultSet.getString("usr_email"));
        user.setLogin(resultSet.getString("usr_name"));
        user.setPassword(resultSet.getString("usr_password_enc"));
        user.setSole(resultSet.getString("usr_sole"));

        return user;
    }
}
