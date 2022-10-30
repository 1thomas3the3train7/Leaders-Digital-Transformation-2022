package com.example.userservice;

import com.example.userservice.Model.Role.RoleDetailed;
import com.example.userservice.Model.User.UserDetailed;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserDetailed userDetailed = new UserDetailed("email","password","name");
		RoleDetailed roleDetailed = new RoleDetailed("ROLE_ADMIN");
		userRepository.save(userDetailed);
		roleRepository.save(roleDetailed);
		roleRepository.appendUserAndRole(userDetailed,roleDetailed);
	}
}
