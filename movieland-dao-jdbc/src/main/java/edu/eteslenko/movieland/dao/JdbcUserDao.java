package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JdbcUserDao implements UserDao {

    private static final UserRowMapper ROW_MAPPER = new UserRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String userSelectByIdQuery;
    private String userSelectByEmailQuery;
    private String userCheckByPassswordQuery;
    private String userSelectByIdsQuery;
    private String userSelectAllQuery;
    private String updateUserQuery;


    public List<User> getAll() {
        logger.debug("Getting all users");
        List<User> users =
                namedJdbcTemplate.query(userSelectAllQuery,
                        ROW_MAPPER);
        return users;
    }

    public void update(User user) {
        logger.debug("Updating user in DB {}", user.getId());
        SqlParameterSource source = new MapSqlParameterSource().addValue("user", user.getId())
                .addValue("password", user.getPassword())
                .addValue("sole", user.getSole());
        namedJdbcTemplate.update(updateUserQuery, source);

    }

    @Override
    public User getById(int id) {
        logger.debug("Getting user from DB by movie id {}", id);
        SqlParameterSource source = new MapSqlParameterSource().addValue("user", id);
        User user =
                namedJdbcTemplate.queryForObject(userSelectByIdQuery,
                        source,
                        ROW_MAPPER);
        return user;
    }

    @Override
    public User getOne(String email) {
        logger.debug("Getting user from DB by email {}", email);
        SqlParameterSource source = new MapSqlParameterSource().addValue("email", email);
        User user =
                namedJdbcTemplate.queryForObject(userSelectByEmailQuery,
                        source,
                        ROW_MAPPER);
        return user;
    }

    public Map<Integer, User> getById(List<Integer> id) {
        logger.debug("Getting user from DB by user ids {}", id);
        SqlParameterSource source = new MapSqlParameterSource().addValue("params", id);
        List<User> user =
                namedJdbcTemplate.query(userSelectByIdsQuery,
                        source,
                        ROW_MAPPER
                );
        Map<Integer, User> usersMap = user.stream().collect(Collectors.toMap(t -> t.getId(), t -> t));
        return usersMap;
    }

    @Override
    public User checkPassword(String enteredPassword) {
        logger.debug("Getting user from DB by entered password");
        SqlParameterSource source = new MapSqlParameterSource().addValue("enc_password", enteredPassword);
        try {
            User user = namedJdbcTemplate.queryForObject(userCheckByPassswordQuery,
                            source,
                            ROW_MAPPER);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Value("${query.userById}")
    public void setUserSelectByIdQuery(String userSelectByIdQuery) {
        this.userSelectByIdQuery = userSelectByIdQuery;
    }

    @Value("${query.userByIds}")
    public void setUserSelectByIdsQuery(String userSelectByIdsQuery) {
        this.userSelectByIdsQuery = userSelectByIdsQuery;
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Value("${query.getAllUsers}")
    public void setUserSelectAllQuery(String userSelectAllQuery) {
        this.userSelectAllQuery = userSelectAllQuery;
    }

    @Value("${query.updateUser}")
    public void setUpdateUserQuery(String updateUserQuery) {
        this.updateUserQuery = updateUserQuery;
    }

    @Value("${query.userByEmail}")
    public void setUserSelectByEmailQuery(String userSelectByEmailQuery) {
        this.userSelectByEmailQuery = userSelectByEmailQuery;
    }

    @Value("${query.userCheckByPassword}")
    public void setUserCheckByPassswordQuery(String userCheckByPassswordQuery) {
        this.userCheckByPassswordQuery = userCheckByPassswordQuery;
    }
}
