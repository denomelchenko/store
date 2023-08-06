package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.models.User;
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

    @PatchMapping("/users/{id}/update-role")
    public String updateRole(@PathVariable("id") int id) {
        User user = userService.getById(id);
        if (user.getRole().equals("ROLE_USER")) {
            user.setRole("ROLE_ADMIN");
        } else if (user.getRole().equals("ROLE_ADMIN")){
            user.setRole("ROLE_USER");
        }
        userService.update(user, id);
        return "redirect:/admin/users";
    }
}
