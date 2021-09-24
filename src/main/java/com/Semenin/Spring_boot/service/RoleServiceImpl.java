package com.Semenin.Spring_boot.service;

import com.Semenin.Spring_boot.dao.RoleDao;
import com.Semenin.Spring_boot.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Set<Role> getRolesById(Long [] id) {
        return roleDao.getRolesById(id);
    }
}
