package com.Semenin.Spring_boot.config;

import com.Semenin.Spring_boot.model.Role;
import com.Semenin.Spring_boot.model.User;
import com.Semenin.Spring_boot.service.RoleService;
import com.Semenin.Spring_boot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    final
    UserService serviceUser;
    final
    RoleService roleService;

    public DbInit(UserService serviceUser, RoleService roleService) {
        this.serviceUser = serviceUser;
        this.roleService = roleService;
    }

    @PostConstruct
    public void Test() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleService.addRole(admin);
        roleService.addRole(user);
        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(admin);
        roleAdmin.add(user);
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(user);
        serviceUser.add(new User("admin", "1", roleAdmin));
        serviceUser.add(new User("user", "1", roleUser));
    }
}
