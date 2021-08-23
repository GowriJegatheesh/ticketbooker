package com.gj.ticketbooker.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.sql.DataSource;
import java.util.Properties;


/*
This class contains the beans required for connecting to database
 */

@Configuration
@EnableJpaRepositories("com.gj.ticketbooker.*")
@EntityScan("com.gj.ticketbooker.*")
@EnableAsync
public class DbConfiguration {
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.show-sql}")
    private String showSQL;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateHbm2ddlAuto;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", hibernateDialect);
        hibernateProperties.put("hibernate.show_sql", showSQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        return hibernateProperties;


    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.gj.ticketbooker");
        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

}
