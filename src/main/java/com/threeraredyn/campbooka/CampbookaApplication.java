package com.threeraredyn.campbooka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CampbookaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampbookaApplication.class, args);
	}

}
