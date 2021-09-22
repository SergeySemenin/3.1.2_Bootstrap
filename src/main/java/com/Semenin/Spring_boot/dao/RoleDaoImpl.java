package com.Semenin.Spring_boot.dao;

import com.Semenin.Spring_boot.model.Role;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }
}
