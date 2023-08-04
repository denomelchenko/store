package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/users";
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/admin/user";
    }
}
