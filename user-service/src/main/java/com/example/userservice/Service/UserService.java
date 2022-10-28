package com.example.userservice.Service;

import com.example.userservice.DTO.UserDTO;
import com.example.userservice.Exception.NotValidRequestException;
import com.example.userservice.Exception.UserAlreadyExistException;
import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Model.User.UserDetailed;
import com.example.userservice.Model.User.UserShort;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Utils.DtoUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Gson gson = new Gson();
    private final DtoUtils dtoUtils;

    public UserService(UserRepository userRepository, DtoUtils dtoUtils) {
        this.userRepository = userRepository;
        this.dtoUtils = dtoUtils;
    }

    public String loginAndValidateUser(final String request){
        if(request == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        final UserDTO userDTO = gson.fromJson(request, UserDTO.class);
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        return loginUser(userDTO);
    }
    private String loginUser(final UserDTO userDTO){
        final UserShort userShort = userRepository.getUserAndRoleShortByEmail(userDTO.getEmail());
        final UserDTO userDTO1 = dtoUtils.UserShortToUserDTO(userShort);
        return gson.toJson(userDTO1);
    }
    public String registerAndValidUser(final String request){
        if(request == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        final UserDTO userDTO = gson.fromJson(request, UserDTO.class);
        if(userDTO.getEmail() == null || userDTO.getPassword() == null
                || userDTO.getFirstName() == null){
            throw new NotValidRequestException("Not valid request (null)");
        }
        return registerUser(userDTO);
    }
    private String registerUser(final UserDTO userDTO){
        final UserShort userShort = userRepository.getUserAndRoleShortByEmail(userDTO.getEmail());
        if(userShort != null){
            throw new UserAlreadyExistException("User already exist");
        }
        final UserDetailed userDetailed = new UserDetailed(
                userDTO.getEmail(),userDTO.getPassword(),userDTO.getFirstName());
        userRepository.save(userDetailed);
        return "User save";
    }
    public String getUserByEmail(final String email){
        final UserDetailed userDetailed = userRepository.getFullUserDetailedByEmail(email);
        if(userDetailed == null){
            throw new UserNotFoundException("User not found");
        }
        final UserDTO userDTO = dtoUtils.UserDetailedToUserDTO(userDetailed);
        return gson.toJson(userDTO);
    }

}
