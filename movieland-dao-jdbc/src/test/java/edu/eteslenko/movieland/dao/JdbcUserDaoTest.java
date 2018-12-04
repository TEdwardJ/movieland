package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class JdbcUserDaoTest {

    private List<Integer> list;
    private List<User> userList;
    private User testUser;

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @InjectMocks
    UserDao jdbcUserDao;

    @Before
    public void init() {
        if (testUser == null) {
            testUser = jdbcUserDao.getById(1);
            list = new ArrayList<Integer>();
            list.add(1);
            list.add(2);
            userList = jdbcUserDao.getById(list).values().stream().collect(Collectors.toList());
        }
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() {

        Mockito.when(jdbcTemplate.queryForObject(Matchers.anyString(), Matchers.any(SqlParameterSource.class), Matchers.any(RowMapper.class))).thenReturn(testUser);
        User actualUser = jdbcUserDao.getById(1);
        Assert.assertEquals(testUser, actualUser);
    }

    @Test
    public void getByIdList() {
        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(SqlParameterSource.class), Matchers.any(RowMapper.class))).thenReturn(userList);
        Map<Integer,User> actualUsers = jdbcUserDao.getById(list);
        Assert.assertEquals(2, actualUsers.entrySet().size());
    }
}