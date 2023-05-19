package com.esi.week10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week10JpaApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("usercredentials_db");
		SpringApplication.run(Week10JpaApplication.class, args);
	}
}
