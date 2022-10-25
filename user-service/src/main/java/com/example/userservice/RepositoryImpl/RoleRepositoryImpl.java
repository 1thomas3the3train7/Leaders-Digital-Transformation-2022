package com.example.userservice.RepositoryImpl;

import com.example.userservice.Model.Role.RoleBase;
import com.example.userservice.Model.Role.RoleShort;
import com.example.userservice.Model.User.UserBase;
import com.example.userservice.Model.User.UserShort;
import com.example.userservice.Repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(RoleBase roleBase) {
        em.persist(roleBase);
    }

    @Override
    @Transactional
    public void delete(RoleBase roleBase) {
        em.remove(em.contains(roleBase) ? roleBase : em.merge(roleBase));
    }

    @Override
    @Transactional
    public void appendUserAndRole(UserBase userBase, RoleBase roleBase) {
        em.createNativeQuery("INSERT INTO user_and_role (user_id,role_id) VALUES (?1,?2)")
                .setParameter(1,userBase.getId())
                .setParameter(2,roleBase.getId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendUserAndRole(Long user_id, Long role_id) {
        em.createNativeQuery("INSERT INTO user_and_role (user_id,role_id) VALUES (?1,?2)")
                .setParameter(1,user_id)
                .setParameter(2,role_id)
                .executeUpdate();
    }
}
