package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Idea.IdeaBase;
import com.example.ideaservice.Model.Idea.IdeaShort;
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

    @Override
    @Transactional
    public void appendUserAndIdea(Long user_id, Long idea_id) {
        em.createNativeQuery("INSERT INTO user_and_idea (user_id,idea_id) VALUES (?1,?2)")
                .setParameter(1,user_id)
                .setParameter(2,idea_id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public IdeaShort getIdeaShortByTokenIdea(String tokenIdea) {
        final IdeaShort idea = em.createQuery("SELECT i FROM IdeaShort i " +
                "WHERE i.tokenIdea = ?1", IdeaShort.class)
                .setParameter(1,tokenIdea)
                .getSingleResult();
        return idea;
    }

    @Override
    @Transactional
    public void appendProjectAndIdea(Long project_id, Long idea_id) {
        em.createNativeQuery("INSERT INTO project_and_idea (project_id,idea_id) VALUES (?1,?2)")
                .setParameter(1,project_id)
                .setParameter(2,idea_id)
                .executeUpdate();
    }
}
