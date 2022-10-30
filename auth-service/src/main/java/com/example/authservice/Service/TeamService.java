package com.example.authservice.Service;

import com.example.authservice.DTO.TeamDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Exception.NotValidRequestException;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final Gson gson = new Gson();
    public String createTeamAndValid(final String teamJSON,final String email){
        if(teamJSON == null || email == null){
            throw new NotValidRequestException();
        }
        final UserDTO user = new UserDTO(email);
        final TeamDTO team = gson.fromJson(teamJSON, TeamDTO.class);
        team.setMainUser(user);
        if(team.getName() == null){
            throw new NotValidRequestException();
        }
        return createTeam(team);
    }
    private String createTeam(final TeamDTO teamDTO){
        return null;
    }
}
