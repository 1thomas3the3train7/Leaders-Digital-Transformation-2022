package com.example.userservice.Utils;

import com.example.userservice.DTO.IdeaDTO;
import com.example.userservice.DTO.RoleDTO;
import com.example.userservice.DTO.UserDTO;
import com.example.userservice.Model.Idea.IdeaDetailed;
import com.example.userservice.Model.Role.RoleDetailed;
import com.example.userservice.Model.Role.RoleShort;
import com.example.userservice.Model.User.UserDetailed;
import com.example.userservice.Model.User.UserShort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoUtils {
    public UserDTO UserShortToUserDTO(final UserShort userShort){
        final UserDTO userDTO = new UserDTO(userShort.getEmail(), userShort.getPassword());
        final List<RoleShort> roleShortList = userShort.getRoles().stream().toList();
        final int size = roleShortList.size();
        final RoleDTO[] roles = new RoleDTO[size];
        for(int i = 0;i < size;i++){
            roles[i] = new RoleDTO(roleShortList.get(i).getName());
        }
        userDTO.setRoles(roles);
        return userDTO;
    }
    public UserDTO UserDetailedToUserDTO(final UserDetailed userDetailed){
        final UserDTO userDTO = new UserDTO(userDetailed.getEmail(), userDetailed.getPassword());
        final List<RoleDetailed> roleDetaileds = userDetailed.getRoles().stream().toList();
        final int size = roleDetaileds.size();
        final RoleDTO[] roleDTOS = new RoleDTO[size];
        for(int i = 0;i < size;i++){
            roleDTOS[i] = new RoleDTO(roleDetaileds.get(i).getName());
        }
        userDTO.setRoles(roleDTOS);
        return userDTO;
    }
    public IdeaDetailed IdeaDTOToIdeaDetailed(final IdeaDTO idea){
        final IdeaDetailed ideaDetailed = new IdeaDetailed(
                Long.parseLong(idea.getId()), idea.getTitle(), idea.getDesc());
        return ideaDetailed;
    }
}
