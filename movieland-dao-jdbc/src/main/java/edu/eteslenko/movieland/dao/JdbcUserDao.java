package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcUserDao implements UserDao {

    private static final UserRowMapper ROW_MAPPER = new UserRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String userSelectByIdQuery;
    private String userSelectByIdsQuery;

    @Override
    public User getById(int id) {
        logger.debug("Getting user from DB by movie id {}", id);
        SqlParameterSource source = new MapSqlParameterSource().addValue("user",id);
        User user =
                namedJdbcTemplate.queryForObject(userSelectByIdQuery,
                                   source,
                                   ROW_MAPPER);
        return user;
    }

    public Map<Integer, User> getById(List<Integer> id) {
        logger.debug("Getting user from DB by user ids {}", id);
        SqlParameterSource source = new MapSqlParameterSource().addValue("params",id);
        List<User> user =
                namedJdbcTemplate.query(userSelectByIdsQuery,
                        source,
                        ROW_MAPPER
                        );
        Map<Integer, User> usersMap = user.stream().collect(Collectors.toMap(t->t.getId(),t->t));
        return usersMap;
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
}
