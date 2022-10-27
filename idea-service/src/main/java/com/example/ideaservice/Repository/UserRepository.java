package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.User.UserBase;
import com.example.ideaservice.Model.User.UserDetailed;
import com.example.ideaservice.Model.User.UserShort;

public interface UserRepository {
    void save(UserBase userBase);
    void delete(UserBase userBase);
    UserShort getUserShortByEmail(String email);
    UserDetailed getUserDetailedByEmail(String email);
}
