package com.example.ideaservice.Service;

import com.example.ideaservice.DTO.IdeaDTO;
import com.example.ideaservice.DTO.UserDTO;
import com.example.ideaservice.Exception.NotValidRequestException;
import com.example.ideaservice.Model.Idea.IdeaDetailed;
import com.example.ideaservice.Model.User.UserDetailed;
import com.example.ideaservice.Model.User.UserShort;
import com.example.ideaservice.Repository.IdeaRepository;
import com.example.ideaservice.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final Gson gson = new Gson();

    public IdeaService(IdeaRepository ideaRepository, UserRepository userRepository, UserService userService) {
        this.ideaRepository = ideaRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public String saveIdeaAndValid(final String request){
        if(request == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        final IdeaDTO ideaDTO = gson.fromJson(request, IdeaDTO.class);
        if(ideaDTO.getDesc() == null || ideaDTO.getUser().getEmail() == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        return saveIdea(ideaDTO);
    }
    private String saveIdea(final IdeaDTO ideaDTO){
        final String email = ideaDTO.getUser().getEmail();
        final IdeaDetailed ideaDetailed = new IdeaDetailed(ideaDTO.getTitle(),ideaDTO.getDesc());
        final UserShort user = userRepository.getUserShortByEmail(email);
        if(user == null){
            final UserDetailed userDetailed = userService.updateUserFromUserService(email);
            ideaRepository.save(ideaDetailed);
            ideaRepository.appendUserAndIdea(userDetailed.getId(), ideaDetailed.getId());
            return "Save Idea";
        }
        ideaRepository.save(ideaDetailed);
        ideaRepository.appendUserAndIdea(user.getId(), ideaDetailed.getId());
        return "Save Idea";
    }
}
