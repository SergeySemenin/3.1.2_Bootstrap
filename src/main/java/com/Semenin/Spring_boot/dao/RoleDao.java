package com.Semenin.Spring_boot.dao;

import com.Semenin.Spring_boot.model.Role;

import java.util.Set;

public interface RoleDao {
    void addRole(Role role);

    Set<Role> getRoles();

    Set<Role> getRolesById(Long [] id);
}
