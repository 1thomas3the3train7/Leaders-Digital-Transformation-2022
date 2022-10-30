package com.example.userservice.Service;

import com.example.userservice.DTO.IdeaDTO;
import com.example.userservice.Exception.NotValidRequestException;
import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Model.Idea.IdeaDetailed;
import com.example.userservice.Model.Idea.IdeaShort;
import com.example.userservice.Model.User.UserShort;
import com.example.userservice.Repository.IdeaRepository;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Utils.DtoUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final DtoUtils dtoUtils;
    private final Gson gson = new Gson();

    public IdeaService(IdeaRepository ideaRepository, UserRepository userRepository, DtoUtils dtoUtils) {
        this.ideaRepository = ideaRepository;
        this.userRepository = userRepository;
        this.dtoUtils = dtoUtils;
    }

    public String updateIdeaAndValid(final String request){
        if(request == null){
            throw new NotValidRequestException();
        }
        final IdeaDTO idea = gson.fromJson(request, IdeaDTO.class);
        if(idea.getUser() == null || idea.getTokenIdea() == null){
            throw new NotValidRequestException();
        }
        return updateIdea(idea);
    }
    private String updateIdea(final IdeaDTO idea){
        final UserShort user = userRepository.getUserShortByEmail(idea.getUser().getEmail());
        final IdeaShort ideaShort = ideaRepository.getIdeaAndUserShortByIdeaId(Long.parseLong(idea.getId()));
        if(user == null){
            throw new UserNotFoundException();
        }
        if(ideaShort != null){
            throw new NotValidRequestException();
        }
        final IdeaDetailed ideaDetailed = dtoUtils.IdeaDTOToIdeaDetailed(idea);
        ideaRepository.save(ideaDetailed);
        ideaRepository.appendUserAndIdea(user.getId(), ideaDetailed.getId());
        return "Idea Save";
    }
}
