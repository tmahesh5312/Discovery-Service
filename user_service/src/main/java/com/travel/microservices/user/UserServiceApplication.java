package com.travel.microservices.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(UserServiceApplication.class, args);

	}

}
