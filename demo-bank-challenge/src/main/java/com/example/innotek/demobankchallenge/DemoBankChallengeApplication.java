package com.example.innotek.demobankchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.innotek*")
public class DemoBankChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBankChallengeApplication.class, args);
	}

}
