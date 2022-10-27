package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Project.ProjectBase;
import com.example.ideaservice.Repository.ProjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(ProjectBase projectBase) {
        em.persist(projectBase);
    }

    @Override
    @Transactional
    public void delete(ProjectBase projectBase) {
        em.remove(em.contains(projectBase) ? projectBase : em.merge(projectBase));
    }
}
