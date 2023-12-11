package com.reactive;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
//@EnableR2dbcRepositories
@SpringBootApplication
@ComponentScan("com.reactive")
public class SpringReactiveApplication  {


	/*// TODO: 24/11/2023 This method will identify the script from resource and will help to create the table using r2dbc repository
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory ) { //ConnectionFactory created an entry point for driver

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer(); //used to setup the database during initialization and clean up the database at the time of destruction
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}*/


	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApplication.class, args);
	}


}
