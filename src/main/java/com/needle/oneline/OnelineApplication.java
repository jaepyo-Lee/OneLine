package com.needle.oneline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OnelineApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnelineApplication.class, args);
	}
}
