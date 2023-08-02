package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;

    @Autowired
    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
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
}
