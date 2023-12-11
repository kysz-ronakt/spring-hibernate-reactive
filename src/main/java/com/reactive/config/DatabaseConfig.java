package com.reactive.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class DatabaseConfig {

    // FIXME: 29/11/2023 
   /* @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(ConnectionFactoryOptions.HOST, "localhost")
                .option(ConnectionFactoryOptions.PORT, 5432)
                .option(ConnectionFactoryOptions.USER, "postgres")
                .option(ConnectionFactoryOptions.PASSWORD, "root")
                .option(ConnectionFactoryOptions.DATABASE, "reactiveDB")
                .build();

        return new PostgresqlConnectionFactoryProvider().create(options);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder().connectionFactory(connectionFactory).build();
    }*/
   /* @Autowired
    private Environment environment;

    @Bean
    public SessionFactory sessionFactory() {
        String url = environment.getProperty("your.datasource.url");
        String username = environment.getProperty("your.datasource.username");
        String password = environment.getProperty("your.datasource.password");

        return hibernateConfiguration(url, username, password).buildSessionFactory();
    }

    @Bean
    public org.hibernate.cfg.Configuration hibernateConfiguration(String url, String username, String password) {
        // Delegate to the HibernateConfig class
        return new HibernateConfig().hibernateConfiguration(url, username, password);
    }
*/

}
