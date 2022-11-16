package com.example.innotek.demobankchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.innotek.demobankchallenge.mapper"})
@EntityScan("com.example.innotek.demobankchallenge.model")
public class DemoBankChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBankChallengeApplication.class, args);
	}

}
