package edu.eteslenko.movieland;


import edu.eteslenko.movieland.dao.JdbcUserDao;
import edu.eteslenko.movieland.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Random;

public class RMain {
    private static JdbcUserDao userDao;
    private static Random r = new Random();
    private static RandomStringGenerator rs = new RandomStringGenerator
            .Builder()
            .withinRange('a', 'z')
            .usingRandom(r::nextInt)
            .build();


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userDao = (JdbcUserDao) context.getBean("userDao");
        List<User> users = userDao.getAll();
        for (int i = 0; i < 11; i++) {
            System.out.println(rs.generate(10));
        }
        for (User user : users) {
            encrypt(user);
            user.setPassword(md5Apache("user" + user.getSole()));

            userDao.update(user);
        }
    }

    private static void encrypt(User user) {
        String sole = rs.generate(10);
        user.setSole(sole);

    }


    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }
}
