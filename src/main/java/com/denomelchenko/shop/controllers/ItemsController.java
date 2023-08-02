package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.ItemService;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public ItemsController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("")
    public String items(Model model) {
        model.addAttribute("items", itemService.getAll());
        return "/items/index";
    }

    @GetMapping("/{id}")
    public String item(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.getById(id));
        return "/items/show";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int id) {
        userService.addItemToCart(itemService.getById(id),
                ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        return "redirect:/items/{id}";
    }
}
