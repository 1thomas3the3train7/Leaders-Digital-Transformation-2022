package com.example.ideaservice.Service;

import com.example.ideaservice.DTO.UserDTO;
import com.example.ideaservice.GrpcClient.UserGrpcClient;
import com.example.ideaservice.Model.User.UserDetailed;
import com.example.ideaservice.Model.User.UserShort;
import com.example.ideaservice.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserGrpcClient userGrpcClient;
    private final Gson gson = new Gson();

    public UserService(UserRepository userRepository, UserGrpcClient userGrpcClient) {
        this.userRepository = userRepository;
        this.userGrpcClient = userGrpcClient;
    }

    public UserDetailed updateUserFromUserService(final String email){
        final String response = userGrpcClient.getUserByEmail(email);
        final UserDTO userDTO = gson.fromJson(response, UserDTO.class);
        final UserDetailed userDetailed = new UserDetailed(userDTO.getEmail(),
                userDTO.getFirstName());
        userRepository.save(userDetailed);
        return userDetailed;
    }
}
