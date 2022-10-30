package com.example.ideaservice;

import com.example.ideaservice.Model.Idea.IdeaDetailed;
import com.example.ideaservice.Model.Project.ProjectDetailed;
import com.example.ideaservice.Model.Tag.TagDetailed;
import com.example.ideaservice.Model.Team.TeamDetailed;
import com.example.ideaservice.Model.Team.TeamShort;
import com.example.ideaservice.Model.User.UserDetailed;
import com.example.ideaservice.Model.User.UserShort;
import com.example.ideaservice.Repository.*;
import com.example.ideaservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class IdeaServiceApplication implements CommandLineRunner {
	@Autowired
	private IdeaRepository ideaRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private TeamRepository teamRepository;

	public static void main(String[] args) {
		SpringApplication.run(IdeaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		IdeaDetailed ideaDetailed = new IdeaDetailed("title","description");
		ideaDetailed.setTokenIdea("tokenIdea");
		UserDetailed user = new UserDetailed("email","name");
		userRepository.save(user);
		ideaRepository.save(ideaDetailed);
		ideaRepository.appendUserAndIdea(user.getId(), ideaDetailed.getId());
		TagDetailed tagDetailed = new TagDetailed();
		tagDetailed.setTagName("JAVA");
		tagRepository.save(tagDetailed);
		ProjectDetailed projectDetailed = new ProjectDetailed("name");
		TeamDetailed teamDetailed = new TeamDetailed();
		teamDetailed.setName("name");
		projectRepository.save(projectDetailed);
		teamRepository.save(teamDetailed);
		teamRepository.appendProjectAndTeam(projectDetailed.getId(), teamDetailed);
		System.out.println("START");
		System.out.println("END");
	}

}
