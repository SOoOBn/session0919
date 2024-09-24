package com.likelion.lionlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LionlibApplication {

	public static void main(String[] args) {
		SpringApplication.run(LionlibApplication.class, args);
	}

}
