package com.example.userservice.Repository;

import com.example.userservice.Model.Role.RoleBase;
import com.example.userservice.Model.Role.RoleShort;
import com.example.userservice.Model.User.UserBase;
import com.example.userservice.Model.User.UserShort;

public interface RoleRepository {
    void save(RoleBase roleBase);
    void delete(RoleBase roleBase);
    void appendUserAndRole(UserBase userBase, RoleBase roleBase);
    void appendUserAndRole(Long user_id, Long role_id);
}
