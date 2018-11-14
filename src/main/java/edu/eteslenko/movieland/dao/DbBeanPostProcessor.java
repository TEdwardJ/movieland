package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.dao.DbProperties;
import org.apache.commons.dbcp.BasicDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DbBeanPostProcessor implements BeanPostProcessor {
    public DataSource configureDataSource(BasicDataSource dataSource) {
        String dbUrl = System.getenv("DATABASE_URL");


        BasicDataSource ds = new BasicDataSource();
        if (dbUrl != null) {

            try {
                URI dbUri = new URI(dbUrl);
                DbProperties dbProperties = new DbProperties();
                //ds.setUsername(dbProperties.getUser());
                //ds.setPassword(dbProperties.getPassword());
                ds.setUrl(dbProperties.getUrl());

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return ds;

        }else{
            DbProperties dbProperties = new DbProperties();
            ds.setDriverClassName(dbProperties.getDriverClassName());
            ds.setUsername(dbProperties.getUser());
            ds.setPassword(dbProperties.getPassword());
            ds.setUrl(dbProperties.getUrl());

        }
        return ds;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) {

        return o;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) {
        if (s.equals("dataSource")) {
            o = configureDataSource((BasicDataSource) o);
        }
        return o;
    }
}
