package com.example.userservice.Utils;

import com.example.userservice.DTO.RoleDTO;
import com.example.userservice.DTO.UserDTO;
import com.example.userservice.Model.Role.RoleShort;
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
}
