package com.Semenin.Spring_boot.controller;

import com.Semenin.Spring_boot.model.User;
import com.Semenin.Spring_boot.service.RoleService;
import com.Semenin.Spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("admin/delete={id}")
    public String delete(@PathVariable("id") Long id) {
        serviceUser.removeUserById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin")
    public String add(@ModelAttribute("user") User user) {
        serviceUser.add(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/edit={id}")
    public String update(@ModelAttribute("user") User user) {
        serviceUser.update(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/edit={id}")
    public String updateGet(@ModelAttribute("user") User user) {
        return "edit";
    }
}