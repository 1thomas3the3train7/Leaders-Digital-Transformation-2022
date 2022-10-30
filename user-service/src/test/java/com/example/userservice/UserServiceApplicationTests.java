package com.example.userservice;

import com.example.userservice.DTO.UserDTO;
import com.example.userservice.Model.Role.RoleDetailed;
import com.example.userservice.Model.User.UserDetailed;
import com.example.userservice.Model.User.UserShort;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
	private final Gson gson = new Gson();
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	void contextLoads() {
	}
	/*@Test
	void forLoginAndRegisterUser(){
		UserDetailed userDetailed = new UserDetailed("email","password","name");
		RoleDetailed roleDetailed = new RoleDetailed();
		roleDetailed.setName("ROLE_ADMIN");
		userRepository.save(userDetailed);
		roleRepository.save(roleDetailed);
		roleRepository.appendUserAndRole(userDetailed,roleDetailed);
		UserShort userShort = userRepository.getUserAndRoleShortByEmail("email");
		if(userShort == null){
			throw new RuntimeException("user null");
		}
		UserDTO userDTO = new UserDTO("email","password");
		UserDTO userDTO1 = new UserDTO("email1","password1");
		userDTO1.setFirstName("name");
		System.out.println(userService.loginAndValidateUser(gson.toJson(userDTO)));
		System.out.println(userService.registerAndValidUser(gson.toJson(userDTO1)));
	}*/

}
