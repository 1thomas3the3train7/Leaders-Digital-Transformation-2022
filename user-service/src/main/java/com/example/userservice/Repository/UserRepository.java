package com.example.userservice.Repository;

import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Model.Role.RoleShort;
import com.example.userservice.Model.User.UserBase;
import com.example.userservice.Model.User.UserShort;

public interface UserRepository {
    void save(UserBase userBase);
    void delete(UserBase userBase);
    UserShort getUserAndRoleShortByEmail(String email);
}
