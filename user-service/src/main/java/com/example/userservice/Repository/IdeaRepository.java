package com.example.userservice.Repository;

import com.example.userservice.Model.Idea.IdeaBase;
import com.example.userservice.Model.Idea.IdeaShort;

public interface IdeaRepository {
    void save(IdeaBase ideaBase);
    void delete(IdeaBase ideaBase);
    void appendUserAndIdea(Long user_id,Long idea_id);
    IdeaShort getIdeaAndUserShortByIdeaId(Long idea_id);
}
