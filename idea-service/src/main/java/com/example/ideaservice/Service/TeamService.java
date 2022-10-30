package com.example.ideaservice.Service;

import com.example.ideaservice.DTO.TeamDTO;
import com.example.ideaservice.DTO.UserDTO;
import com.example.ideaservice.Exception.NotValidRequestException;
import com.example.ideaservice.Model.Team.TeamDetailed;
import com.example.ideaservice.Model.User.UserDetailed;
import com.example.ideaservice.Model.User.UserShort;
import com.example.ideaservice.Repository.TeamRepository;
import com.example.ideaservice.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final DtoUtils dtoUtils;
    private final Gson gson = new Gson();

    public TeamService(UserService userService, UserRepository userRepository, TeamRepository teamRepository, DtoUtils dtoUtils) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.dtoUtils = dtoUtils;
    }

    public String createTeamAndValid(final String teamJSON){
        if(teamJSON == null){
            throw new NotValidRequestException();
        }
        final TeamDTO team = gson.fromJson(teamJSON, TeamDTO.class);
        if(team.getName() == null || team.getMainUser() == null){
            throw new NotValidRequestException();
        }
        return createTeam(team);
    }
    private String createTeam(final TeamDTO teamDTO){
        final UserDTO userDTO = teamDTO.getMainUser();
        final String email = userDTO.getEmail();
        final UserShort userShort = userRepository.getUserShortByEmail(email);
        final Long userId;
        if(userShort == null){
            final UserDetailed userDetailed = userService.updateUserFromUserService(email);
            userId = userDetailed.getId();
        } else {
            userId = userShort.getId();
        }
        final TeamDetailed teamDetailed = dtoUtils.TeamDTOToTeamDetailed(teamDTO);
        teamRepository.save(teamDetailed);
        teamRepository.appendUserAndTeam(userId, teamDetailed.getId());
        return "Team save";
    }
}
