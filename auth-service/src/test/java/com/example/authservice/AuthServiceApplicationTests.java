package com.example.authservice;

import com.example.authservice.DTO.IdeaDTO;
import com.example.authservice.DTO.ProjectDTO;
import com.example.authservice.DTO.TagDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Exception.UserNotFoundException;
import com.example.authservice.Service.IdeaService;
import com.example.authservice.Service.ProjectService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;

@SpringBootTest
class AuthServiceApplicationTests {
	@Autowired
	private IdeaService ideaService;
	@Autowired
	private ProjectService projectService;
	private final Gson gson = new Gson();

	/*@Test
	void contextLoads() {
	}
	@Test
	void forIdeaSave(){
		IdeaDTO ideaDTO = new IdeaDTO();
		ideaDTO.setDesc("desc");
		ideaDTO.setTitle("title");
		ideaService.createIdeaAndValid(gson.toJson(ideaDTO),"email");
	}
	@Test
	void forIdeaSave1(){
		try {
			IdeaDTO ideaDTO = new IdeaDTO();
			ideaDTO.setDesc("desc");
			ideaDTO.setTitle("title");
			ideaService.createIdeaAndValid(gson.toJson(ideaDTO),"email1");
		} catch (UserNotFoundException u){
			System.out.println("completed");
		}
	}
	@Test
	void forProjectCreate(){
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setName("name");
		IdeaDTO ideaDTO = new IdeaDTO();
		ideaDTO.setTokenIdea("tokenIdea");
		UserDTO userDTO = new UserDTO("email");
		projectDTO.setMainUser(userDTO);
		TagDTO tag = new TagDTO();
		tag.setName("java");
		TagDTO[] tags = new TagDTO[1];
		tags[0] = tag;
		IdeaDTO[] ideas = new IdeaDTO[1];
		ideas[0] = ideaDTO;
		projectDTO.setIdeas(ideas);
		projectDTO.setTags(tags);
		projectService.createProjectAndValid(gson.toJson(projectDTO),"email");
	}*/

}
