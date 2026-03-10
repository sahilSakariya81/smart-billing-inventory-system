package com.example.billGenerationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BillGenerationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillGenerationSystemApplication.class, args);
	}

}
