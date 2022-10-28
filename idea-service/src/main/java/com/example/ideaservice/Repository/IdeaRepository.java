package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Idea.IdeaBase;
import com.example.ideaservice.Model.Idea.IdeaShort;

public interface IdeaRepository {
    void save(IdeaBase ideaBase);
    void delete(IdeaBase ideaBase);
    void appendUserAndIdea(Long user_id,Long idea_id);
    IdeaShort getIdeaShortByTokenIdea(String tokenIdea);
    void appendProjectAndIdea(Long project_id,Long idea_id);
}
