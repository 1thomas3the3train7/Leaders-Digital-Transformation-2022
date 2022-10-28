package com.example.ideaservice.RepositoryImpl;

import com.example.ideaservice.Model.Tag.TagBase;
import com.example.ideaservice.Model.Tag.TagShort;
import com.example.ideaservice.Repository.TagRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TagRepositoryImpl implements TagRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(TagBase tagBase) {
        em.persist(tagBase);
    }

    @Override
    @Transactional
    public void delete(TagBase tagBase) {
        em.remove(em.contains(tagBase) ? tagBase : em.merge(tagBase));
    }

    @Override
    @Transactional(readOnly = true)
    public TagShort getTagByTagName(String tagnName) {
        final TagShort tag = em.createQuery("SELECT t FROM TagShort t WHERE t.tagName = ?1", TagShort.class)
                .setParameter(1,tagnName)
                .getSingleResult();
        return tag;
    }

    @Override
    @Transactional
    public void appendProjectAndTag(Long project_id, Long tag_id) {
        em.createNativeQuery("INSERT INTO project_and_tag (project_id,tag_id) VALUES (?1,?2)")
                .setParameter(1,project_id)
                .setParameter(2,tag_id)
                .executeUpdate();
    }
}
