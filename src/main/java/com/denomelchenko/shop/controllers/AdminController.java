package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.services.ItemService;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public AdminController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping
    public String adminPage() {
        return "/admin/admin-page";
    }

    @GetMapping("/items")
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "/admin/items/index";
    }

    @GetMapping("/items/{id}")
    public String getItemInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.getById(id));
        return "/admin/items/show";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/users/index";
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/admin/users/show";
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
