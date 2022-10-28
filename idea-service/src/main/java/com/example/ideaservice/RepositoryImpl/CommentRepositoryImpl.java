package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Comment.CommentBase;
import com.example.ideaservice.Repository.CommentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(CommentBase commentBase) {
        em.persist(commentBase);
    }

    @Override
    @Transactional
    public void delete(CommentBase commentBase) {
        em.remove(em.contains(commentBase) ? commentBase : em.merge(commentBase));
    }
}
