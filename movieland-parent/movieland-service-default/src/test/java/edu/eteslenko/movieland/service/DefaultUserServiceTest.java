package edu.eteslenko.movieland.service;

import edu.eteslenko.movieland.dao.GenreDao;
import edu.eteslenko.movieland.dao.UserDao;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class DefaultUserServiceTest {
    private User testUser;

    @Mock
    private UserDao jdbcUserDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Before
    public void init(){
        if (testUser==null) {
            testUser = userService.getById(1);
        }
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() {
        Mockito.when(userService.getById(anyInt())).thenReturn(testUser);
        User actualUser = userService.getById(8);
        Assert.assertEquals(testUser,actualUser);
    }
}