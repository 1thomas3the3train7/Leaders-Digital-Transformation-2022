package com.example.userservice.Repository;

import com.example.userservice.Model.Idea.IdeaBase;

public interface IdeaRepository {
    void save(IdeaBase ideaBase);
    void delete(IdeaBase ideaBase);
}
