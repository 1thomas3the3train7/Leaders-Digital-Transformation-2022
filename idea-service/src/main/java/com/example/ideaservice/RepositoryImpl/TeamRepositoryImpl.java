package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Team.TeamBase;
import com.example.ideaservice.Repository.TeamRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
}
