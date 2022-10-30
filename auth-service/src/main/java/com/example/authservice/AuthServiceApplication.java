package com.example.authservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
