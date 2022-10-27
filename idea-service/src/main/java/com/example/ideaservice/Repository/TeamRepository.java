package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Team.TeamBase;

public interface TeamRepository {
    void save(TeamBase teamBase);
    void delete(TeamBase teamBase);
}
