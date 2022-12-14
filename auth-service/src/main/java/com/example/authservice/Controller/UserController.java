package com.example.authservice.Controller;

import com.example.authservice.DTO.JwtDTO;
import com.example.authservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<JwtDTO> login(@RequestBody String request){
        System.out.println("Asdsad");
        return new  ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }
    @PostMapping(value = "/admin")
    public ResponseEntity<String> admin(Principal principal){
        System.out.println(principal.getName());
        return new ResponseEntity<>("admin",HttpStatus.OK);
    }
    @PostMapping(value = "/auth")
    public ResponseEntity<String> auth(){
        return new ResponseEntity<>("auth",HttpStatus.OK);
    }
    @PostMapping(value = "/getrefreshtoken")
    public ResponseEntity<JwtDTO> getAccessToken(@RequestBody String request){
        return new ResponseEntity<>(userService.getAccessAndRefreshToken(request), HttpStatus.OK);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody String request){
        return new ResponseEntity<>(userService.registerAndValid(request),HttpStatus.OK);
    }
}
