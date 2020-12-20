package com.technoWeb.Tp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TechnoWebTpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnoWebTpApplication.class, args);
	}

}
