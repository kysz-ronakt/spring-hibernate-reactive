package com.reactive.config;

import com.reactive.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    // FIXME: 29/11/2023
/*
    Configuration hibernateConfig = new Configuration();
    @Bean
    private SessionFactory hibernateConfiguration() {
        // 1. Create properties with config data
        Properties hibernateProps = new Properties();

        // URL
        hibernateProps.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hibernateReactiveD");

        // Credentials
        hibernateProps.put("hibernate.connection.username", "postgres");
        hibernateProps.put("hibernate.connection.password", "root");

        // Schema generation
        hibernateProps.put("javax.persistence.schema-generation.database.action", "create");

        // Dialect
        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        // 2. Create Hibernate configuration

        hibernateConfig.setProperties(hibernateProps);
        hibernateConfig.addAnnotatedClass(Task.class);

        // 3. Create ServiceRegistry
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateConfig.getProperties())
                .build();

        // 4. Create SessionFactory
        SessionFactory sessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);

        // 5. Return SessionFactory or perform additional setup if needed
        return sessionFactory;
    }
*/

    @Bean
    public SessionFactory customSessionFactory() {
        // Replace these with your actual database connection details
        String url = "jdbc:postgresql://localhost:5432/reactiveDB";
        String username = "postgres";
        String password = "root";

        return hibernateConfiguration(url, username, password).buildSessionFactory();
    }

    @Bean
    public Configuration hibernateConfiguration(String url, String username, String password) {
        // Create properties with config data
        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.connection.url", url);
        hibernateProps.put("hibernate.connection.username", username);
        hibernateProps.put("hibernate.connection.password", password);
        hibernateProps.put("javax.persistence.schema-generation.database.action", "update");
        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        // Create Hibernate configuration
        Configuration hibernateConfig = new Configuration();
        hibernateConfig.setProperties(hibernateProps);
        hibernateConfig.addAnnotatedClass(Task.class);
        hibernateConfig.addAnnotatedClass(Author.class);
        hibernateConfig.addAnnotatedClass(Book.class);
        hibernateConfig.addAnnotatedClass(BookInfo.class);
        hibernateConfig.addAnnotatedClass(Review.class);
        hibernateConfig.addAnnotatedClass(Student.class);
        hibernateConfig.addAnnotatedClass(Tutorial.class);

        return hibernateConfig;
    }


}
