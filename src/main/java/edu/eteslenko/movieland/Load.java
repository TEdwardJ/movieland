package edu.eteslenko.movieland;

import edu.eteslenko.movieland.dao.JdbcMovieDao;
import edu.eteslenko.movieland.dao.MovieDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;

public class Load {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        JdbcMovieDao dao = (JdbcMovieDao)context.getBean("jdbcMovieDao");

//        dao.loadAll();

    }
}
