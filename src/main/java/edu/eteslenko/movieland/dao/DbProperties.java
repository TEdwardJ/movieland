package edu.eteslenko.movieland.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class DbProperties {
    private String driverClassName;
    private String url;
    private String user;
    private String password;
    private String server;
    private String database;
    private int port;

    public DbProperties() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        String dbUrl = System.getenv("DATABASE_URL");
        if(dbUrl==null) {
            driverClassName = props.getProperty("db.driverClassName");
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        }else{
            URI dbUri = null;
            try {
                dbUri = new URI(dbUrl);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            String userInfo = dbUri.getUserInfo();
            url = dbUrl;
            user = userInfo.substring(0,userInfo.indexOf(":"));
            password = userInfo.substring(userInfo.indexOf(":")+1);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getServer() {
        return server;
    }

    public String getDatabase() {
        return database;
    }

    public int getPort() {
        return port;
    }

    public String getDriverClassName() {
        return driverClassName;
    }
}