package com.Semenin.Spring_boot.controller;

import com.Semenin.Spring_boot.model.Role;
import com.Semenin.Spring_boot.model.User;
import com.Semenin.Spring_boot.service.RoleService;
import com.Semenin.Spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    final
    UserService serviceUser;
    final
    RoleService roleService;

    public UserController(UserService serviceUser, RoleService roleService) {
        this.serviceUser = serviceUser;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = serviceUser.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String adminAllUsers(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", serviceUser.getAllUsers());
        return "admin";
    }

    @GetMapping("admin/new")
    public String newUser(Model model, @ModelAttribute("user") User user) {
        Set<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "new";
    }

    @PostMapping("admin/new")
    public String addUser(@RequestParam("select") Long[] select, @ModelAttribute("user") User user) {
        Set<Role> roles = roleService.getRolesById(select[0]);
        user.setRoles(roles);
        serviceUser.add(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete={id}")
    public String delete(@PathVariable("id") Long id) {
        serviceUser.removeUserById(id);
        return "redirect:/admin";
    }

    @PostMapping("admin/edit={id}")
    public String update(@RequestParam("select") Long[] select, @ModelAttribute("user") User user) {
        Set<Role> roles = roleService.getRolesById(select[0]);
        user.setRoles(roles);
        serviceUser.update(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/edit={id}")
    public String updateGet(@ModelAttribute("user") User user, Model model) {
        Set<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "edit";
    }
}