package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.User.UserBase;
import com.example.ideaservice.Model.User.UserDetailed;
import com.example.ideaservice.Model.User.UserShort;
import com.example.ideaservice.Repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(UserBase userBase) {
        em.persist(userBase);
    }

    @Override
    @Transactional
    public void delete(UserBase userBase) {
        em.merge(em.contains(userBase) ? userBase : em.merge(userBase));
    }

    @Override
    @Transactional(readOnly = true)
    public UserShort getUserShortByEmail(String email) {
        //TODO catch exc
        final UserShort userShort = em.createQuery("SELECT u FROM UserShort u " +
                        "WHERE u.email = ?1", UserShort.class)
                .setParameter(1,email)
                .getSingleResult();
        return userShort;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailed getUserDetailedByEmail(String email) {
        final UserDetailed userDetailed = em.createQuery("SELECT u FROM UserDetailed u " +
                "WHERE u.email = ?1", UserDetailed.class)
                .setParameter(1,email)
                .getSingleResult();
        return userDetailed;
    }

    @Override
    @Transactional
    public void appendProjectAndUser(Long project_id, Long user_id) {
        em.createNativeQuery("INSERT INTO user_and_project (project_id,user_id) VALUES (?1,?2)")
                .setParameter(1,project_id)
                .setParameter(2,user_id)
                .executeUpdate();
    }
}
