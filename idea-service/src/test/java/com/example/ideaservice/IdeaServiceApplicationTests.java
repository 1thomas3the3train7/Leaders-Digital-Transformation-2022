package com.example.ideaservice;

import com.example.ideaservice.Model.Project.ProjectDetailed;
import com.example.ideaservice.Model.Team.TeamDetailed;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdeaServiceApplicationTests {

	@Test
	void contextLoads() {
	}
    @Test
    void forAppendMethod(){
        ProjectDetailed projectDetailed = new ProjectDetailed();
        TeamDetailed teamDetailed = new TeamDetailed();

    }

}
