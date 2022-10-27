package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Idea.IdeaBase;
import com.example.ideaservice.Repository.IdeaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IdeaRepositoryImpl implements IdeaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(IdeaBase ideaBase) {
        em.persist(ideaBase);
    }

    @Override
    @Transactional
    public void delete(IdeaBase ideaBase) {
        em.remove(em.contains(ideaBase) ? ideaBase : em.merge(ideaBase));
    }
}