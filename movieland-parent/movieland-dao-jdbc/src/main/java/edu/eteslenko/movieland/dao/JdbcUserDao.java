package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao {

    private static final UserRowMapper ROW_MAPPER = new UserRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    private String userSelectByIdQuery;

    @Override
    public User getById(int id) {
        logger.debug("Getting user from DB by movie id {}", id);
        User user =
                jdbcTemplate.query(userSelectByIdQuery,
                                   t-> t.next()?ROW_MAPPER.mapRow(t,1):null,
                                   id);
        return user;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${query.userById}")
    public void setUserSelectByIdQuery(String userSelectByIdQuery) {
        this.userSelectByIdQuery = userSelectByIdQuery;
    }
}
