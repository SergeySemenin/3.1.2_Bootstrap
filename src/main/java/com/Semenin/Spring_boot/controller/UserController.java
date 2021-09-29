package com.Semenin.Spring_boot.controller;

import com.Semenin.Spring_boot.model.Role;
import com.Semenin.Spring_boot.model.User;
import com.Semenin.Spring_boot.service.RoleService;
import com.Semenin.Spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = serviceUser.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String adminAllUsers(Principal principal, Model model) {
        model.addAttribute("users", serviceUser.getAllUsers());
        User user = serviceUser.findByUserName(principal.getName());
        model.addAttribute("user", user);
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        Set<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "admin";
    }

    @PostMapping("/admin")
    public String addUser(@RequestParam("select[]") Long[] select, @ModelAttribute("user") User user) {
        Set<Role> roles = roleService.getRolesById(select);
        user.setRoles(roles);
        serviceUser.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin={id}")
    public String delete(@PathVariable("id") Long id) {
        serviceUser.removeUserById(id);
        return "redirect:/admin";
    }

    @PatchMapping("admin/edit={id}")
    public String update(@RequestParam("select[]") Long[] select, @ModelAttribute("user") User user) {
        Set<Role> roles = roleService.getRolesById(select);
        user.setRoles(roles);
        serviceUser.update(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/edit={id}")
    public String updateGet(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model) {
        Set<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", serviceUser.getUserById(id));
        return "edit";
    }
}