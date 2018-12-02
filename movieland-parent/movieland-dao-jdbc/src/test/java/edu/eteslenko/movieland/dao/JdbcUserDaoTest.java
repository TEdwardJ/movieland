package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class JdbcUserDaoTest {

    private User testUser;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @InjectMocks
    UserDao jdbcUserDao;
    @Before
    public void init(){
        if (testUser==null) {
            testUser = jdbcUserDao.getById(1);
        }
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getById() {

        Mockito.when(jdbcTemplate.query(Matchers.anyString(), Matchers.any(ResultSetExtractor.class),anyInt())).thenReturn(testUser);
        User actualUser = jdbcUserDao.getById(8);
        Assert.assertEquals(testUser,actualUser);
    }
}