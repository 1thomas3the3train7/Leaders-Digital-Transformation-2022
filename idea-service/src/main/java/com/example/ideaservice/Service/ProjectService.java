package com.example.ideaservice.Service;

import com.example.ideaservice.DTO.IdeaDTO;
import com.example.ideaservice.DTO.ProjectDTO;
import com.example.ideaservice.DTO.TagDTO;
import com.example.ideaservice.Exception.IdeaNotFoundException;
import com.example.ideaservice.Exception.NotValidRequestException;
import com.example.ideaservice.Exception.TagNotFoundException;
import com.example.ideaservice.Model.Idea.IdeaShort;
import com.example.ideaservice.Model.Project.ProjectDetailed;
import com.example.ideaservice.Model.Tag.TagShort;
import com.example.ideaservice.Model.User.UserShort;
import com.example.ideaservice.Repository.IdeaRepository;
import com.example.ideaservice.Repository.ProjectRepository;
import com.example.ideaservice.Repository.TagRepository;
import com.example.ideaservice.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final Gson gson = new Gson();
    private final ProjectRepository projectRepository;
    private final DtoUtils dtoUtils;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final IdeaRepository ideaRepository;

    public ProjectService(ProjectRepository projectRepository, DtoUtils dtoUtils, UserRepository userRepository,
                          TagRepository tagRepository, IdeaRepository ideaRepository) {
        this.projectRepository = projectRepository;
        this.dtoUtils = dtoUtils;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.ideaRepository = ideaRepository;
    }

    public String createProjectAndValid(final String projectJSON){
        if(projectJSON == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        final ProjectDTO projectDTO = gson.fromJson(projectJSON, ProjectDTO.class);
        if(projectDTO.getName() == null || projectDTO.getMainUser().getEmail() == null
        || projectDTO.getTags() == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        return createProject(projectDTO);
    }
    private String createProject(final ProjectDTO projectDTO){
        List<Long> idTag = new ArrayList<>();
        List<Long> idIdea = new ArrayList<>();
        for(TagDTO t : projectDTO.getTags()){
            final TagShort tagShort = tagRepository.getTagByTagName(t.getName());
            if(tagShort != null){
                idTag.add(tagShort.getId());
            }
        }
        if(idTag.size() < 1){
            throw new TagNotFoundException("Tag not found or not valid request");
        }
        for(IdeaDTO i : projectDTO.getIdeas()){
            final IdeaShort idea = ideaRepository.getIdeaShortByTokenIdea(i.getTokenIdea());
            if(idea != null){
                idIdea.add(idea.getId());
            }
        }
        if(idIdea.size() < 1){
            throw new IdeaNotFoundException("Idea not found or not valid request");
        }
        final ProjectDetailed projectDetailed = dtoUtils.ProjectDTOToProjectDetailed(projectDTO);
        final String email = projectDTO.getMainUser().getEmail();
        final UserShort user = userRepository.getUserShortByEmail(email);
        //TODO check user
        projectRepository.save(projectDetailed);
        userRepository.appendProjectAndUser(projectDetailed.getId(), user.getId());
        idTag.forEach(i -> {
            tagRepository.appendProjectAndTag(projectDetailed.getId(), i);
        });
        idIdea.forEach(i -> {
            ideaRepository.appendProjectAndIdea(projectDetailed.getId(), i);
        });
        return "Save Project";
    }
}
