package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Team.TeamBase;
import com.example.ideaservice.Model.Team.TeamDetailed;
import com.example.ideaservice.Model.Team.TeamShort;
import com.example.ideaservice.Repository.TeamRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(TeamBase teamBase) {
        em.persist(teamBase);
    }

    @Override
    @Transactional
    public void delete(TeamBase teamBase) {
        em.remove(em.contains(teamBase) ? teamBase : em.merge(teamBase));
    }

    @Override
    @Transactional
    public void appendProjectAndTeam(Long project_id, TeamBase teamBase) {
        em.createQuery("UPDATE ProjectDetailed p SET p.team = ?1 WHERE p.id = ?2")
                .setParameter(1,teamBase)
                .setParameter(2,project_id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamShort getTeamShortByName(String name) {
        try {
            final TeamShort teamShort = em.createQuery("SELECT t FROM TeamShort t WHERE t.name = ?1", TeamShort.class)
                    .setParameter(1,name)
                    .getSingleResult();
            return teamShort;
        } catch (NoResultException n){
            System.out.println(n.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDetailed getTeamDetailedByName(String name) {
        try {
            final TeamDetailed team = em.createQuery("SELECT t FROM TeamDetailed t " +
                            "JOIN FETCH t.project WHERE t.name = ?1", TeamDetailed.class)
                    .setParameter(1,name)
                    .getSingleResult();
            return team;
        } catch (NoResultException n){
            System.out.println(n.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void appendUserAndTeam(Long user_id, Long team_id) {
        em.createNativeQuery("INSERT INTO user_and_team (user_id,team_id) VALUES (?1,?2)")
                .setParameter(1,user_id)
                .setParameter(2,team_id)
                .executeUpdate();
    }
}
