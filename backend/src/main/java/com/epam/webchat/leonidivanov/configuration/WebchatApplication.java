package com.epam.webchat.leonidivanov.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.epam.webchat.leonidivanov.datalayer.entity"})
@EnableJpaRepositories("com.epam.webchat.leonidivanov.datalayer.repository")
public class WebchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebchatApplication.class, args);
	}

}
