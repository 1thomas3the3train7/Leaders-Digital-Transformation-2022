package com.example.authservice.Controller;

import com.example.authservice.Service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createProject(@RequestBody String request, Principal principal){
        return new ResponseEntity<>(projectService.createProjectAndValid(request,principal.getName()), HttpStatus.OK);
    }
}
