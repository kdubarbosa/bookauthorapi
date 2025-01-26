package com.example.bookauthorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.bookauthorapi.domain.repository")
@SpringBootApplication
public class BookauthorapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookauthorapiApplication.class, args);
	}

}
