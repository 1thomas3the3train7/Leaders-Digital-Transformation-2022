package com.example.userservice.RepositoryImpl;

import com.example.userservice.Model.Idea.IdeaBase;
import com.example.userservice.Model.Idea.IdeaShort;
import com.example.userservice.Repository.IdeaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public IdeaShort getIdeaAndUserShortByIdeaId(Long idea_id) {
        try {
            final IdeaShort idea = em.createQuery("SELECT i FROM IdeaShort i " +
                            "JOIN FETCH i.user WHERE i.idea_id = ?1", IdeaShort.class)
                    .setParameter(1,idea_id)
                    .getSingleResult();
            return idea;
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
