package com.example.authservice.Service;

import com.example.authservice.DTO.IdeaDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Exception.NotValidRequestException;
import com.example.authservice.Grpc.IdeaGrpcClient;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class IdeaService {
    private final IdeaGrpcClient ideaGrpcClient;
    private final Gson gson = new Gson();

    public IdeaService(IdeaGrpcClient ideaGrpcClient) {
        this.ideaGrpcClient = ideaGrpcClient;
    }

    public String createIdeaAndValid(final String idea, String email){

        if(idea == null){
            throw new NotValidRequestException("No valid request (null)");
        }
        final IdeaDTO ideaDTO = gson.fromJson(idea, IdeaDTO.class);
        ideaDTO.setUser(new UserDTO(email));
        if(ideaDTO.getDesc() == null){
            throw new NotValidRequestException("No valid request (null)");
        }
        return createIdea(ideaDTO);
    }
    private String createIdea(final IdeaDTO ideaDTO){
        return ideaGrpcClient.saveIdea(gson.toJson(ideaDTO));
    }
}
