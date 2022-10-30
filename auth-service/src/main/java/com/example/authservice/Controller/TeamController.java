package com.example.authservice.Controller;

import com.example.authservice.Service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createTeam(@RequestBody String request, Principal principal){
        return new ResponseEntity<>(teamService.createTeamAndValid(request,principal.getName()), HttpStatus.OK);
    }
}
