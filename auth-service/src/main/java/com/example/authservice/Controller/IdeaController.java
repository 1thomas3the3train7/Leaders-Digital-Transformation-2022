package com.example.authservice.Controller;

import com.example.authservice.Service.IdeaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/idea")
public class IdeaController {
    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveIdea(@RequestBody String request, Principal principal){
        return new ResponseEntity<>(ideaService.createIdeaAndValid(request,principal), HttpStatus.OK);
    }
}
