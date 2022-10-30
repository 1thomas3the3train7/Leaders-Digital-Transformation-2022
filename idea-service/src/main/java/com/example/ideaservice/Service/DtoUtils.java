package com.example.ideaservice.Service;

import com.example.ideaservice.DTO.IdeaDTO;
import com.example.ideaservice.DTO.ProjectDTO;
import com.example.ideaservice.DTO.TeamDTO;
import com.example.ideaservice.Model.Idea.IdeaDetailed;
import com.example.ideaservice.Model.Project.ProjectDetailed;
import com.example.ideaservice.Model.Team.TeamDetailed;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {
    public IdeaDetailed IdeaDTOToIdeaDetailed(final IdeaDTO ideaDTO){
        final IdeaDetailed ideaDetailed = new IdeaDetailed();
        return null;
    }
    public ProjectDetailed ProjectDTOToProjectDetailed(final ProjectDTO projectDTO){
        final ProjectDetailed projectDetailed = new ProjectDetailed(projectDTO.getName());
        return projectDetailed;
    }
    public TeamDetailed TeamDTOToTeamDetailed(final TeamDTO teamDTO){
        final TeamDetailed teamDetailed = new TeamDetailed(teamDTO.getName());
        return teamDetailed;
    }
}
