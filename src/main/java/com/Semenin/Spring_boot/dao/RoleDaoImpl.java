package com.Semenin.Spring_boot.dao;

import com.Semenin.Spring_boot.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public Set<Role> getRoles() {
        List<Role> roleList = em
                .createQuery("Select u from Role u", Role.class)
                .getResultList();
        return new HashSet<>(roleList);
    }

    @Override
    public Set<Role> getRolesById(Long [] id) {
        Set<Role> roleList = new HashSet<>();
        for (Long roleId: id){
            Role role = em
                    .getReference(Role.class, roleId);
            roleList.add(role);
        }
        return roleList;
    }
}
