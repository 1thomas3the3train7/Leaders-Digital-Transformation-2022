package com.example.userservice.RepositoryImpl;

import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Model.User.UserBase;
import com.example.userservice.Model.User.UserDetailed;
import com.example.userservice.Model.User.UserShort;
import com.example.userservice.Repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    @org.springframework.transaction.annotation.Transactional
    public void delete(UserBase userBase) {
        em.remove(em.contains(userBase) ? userBase : em.merge(userBase));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserShort getUserAndRoleShortByEmail(String email) {
        try {
            final UserShort userShort = em.createQuery("SELECT u FROM UserShort u " +
                            "JOIN FETCH u.roles WHERE u.email = ?1", UserShort.class)
                    .setParameter(1,email)
                    .getSingleResult();
            return userShort;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserShort getUserAndIdeaShortByEmail(String email) {
        try {
            final UserShort userShort = em.createQuery("SELECT u FROM UserShort u JOIN FETCH u.ideas " +
                            "WHERE u.email = ?1", UserShort.class)
                    .setParameter(1,email)
                    .getSingleResult();
            return userShort;
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailed getFullUserDetailedByEmail(String email) {
        try {
            final UserDetailed user = em.createQuery("SELECT u FROM UserDetailed u " +
                            "JOIN FETCH u.roles WHERE u.email = ?1", UserDetailed.class)
                    .setParameter(1,email)
                    .getSingleResult();
            return user;
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserShort getUserShortByEmail(String email) {
        try {
            final UserShort user = em.createQuery("SELECT u FROM UserShort u WHERE LOWER(u.email) = ?1", UserShort.class)
                    .setParameter(1,email)
                    .getSingleResult();
            return user;
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
