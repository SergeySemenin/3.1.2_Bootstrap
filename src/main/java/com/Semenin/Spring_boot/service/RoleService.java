package com.Semenin.Spring_boot.service;

import com.Semenin.Spring_boot.model.Role;

import java.util.Set;

public interface RoleService {
    void addRole(Role role);

    Set<Role> getRoles();

    Set<Role> getRolesById(Long [] id);
}
