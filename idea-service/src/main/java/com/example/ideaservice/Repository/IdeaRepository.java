package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Idea.IdeaBase;

public interface IdeaRepository {
    void save(IdeaBase ideaBase);
    void delete(IdeaBase ideaBase);
}
