package com.project.HospitalFrontDesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HospitalFrontDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalFrontDeskApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
	     return new RestTemplate();
	}
}
