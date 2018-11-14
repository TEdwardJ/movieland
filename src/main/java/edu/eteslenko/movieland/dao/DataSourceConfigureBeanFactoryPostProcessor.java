package edu.eteslenko.movieland.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.sql.DataSource;

public class DataSourceConfigureBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private DbProperties dbProperties;


    public DataSourceConfigureBeanFactoryPostProcessor(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        for (String beanDefinitionName : configurableListableBeanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            if (beanDefinitionName.equals("baseDataSource")) {
                beanDefinition.getPropertyValues().add("driverClassName", dbProperties.getDriverClassName());
                beanDefinition.getPropertyValues().add("url", dbProperties.getUrl());
                beanDefinition.getPropertyValues().add("username", dbProperties.getUser());
                beanDefinition.getPropertyValues().add("password", dbProperties.getPassword());
            }
        }
    }
}
