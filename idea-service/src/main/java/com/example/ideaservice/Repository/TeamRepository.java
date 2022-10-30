package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Team.TeamBase;
import com.example.ideaservice.Model.Team.TeamDetailed;
import com.example.ideaservice.Model.Team.TeamShort;

public interface TeamRepository {
    void save(TeamBase teamBase);
    void delete(TeamBase teamBase);
    void appendProjectAndTeam(Long project_id,TeamBase teamBase);
    TeamShort getTeamShortByName(String name);
    TeamDetailed getTeamDetailedByName(String name);
    void appendUserAndTeam(Long user_id,Long team_id);
}
