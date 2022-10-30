package com.example.authservice.Service;

import com.example.authservice.DTO.ProjectDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Exception.NotValidRequestException;
import com.example.authservice.Grpc.ProjectGrpcClient;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProjectService {
    private final Gson gson = new Gson();
    private final ProjectGrpcClient projectGrpcClient;

    public ProjectService(ProjectGrpcClient projectGrpcClient) {
        this.projectGrpcClient = projectGrpcClient;
    }

    public String createProjectAndValid(final String request, final String email){
        if(request == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        final ProjectDTO projectDTO = gson.fromJson(request, ProjectDTO.class);
        if(projectDTO.getIdeas() == null || projectDTO.getTags() == null
        || projectDTO.getName() == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        projectDTO.setMainUser(new UserDTO(email));
        return createProject(projectDTO);
    }
    private String createProject(final ProjectDTO projectDTO){
        return projectGrpcClient.createProject(gson.toJson(projectDTO));
    }
}
