package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.models.Item;
import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.ItemService;
import com.denomelchenko.shop.services.UserItemService;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserService userService;
    private final ItemService itemService;
    private final UserItemService userItemService;

    @Autowired
    public AdminController(UserService userService, ItemService itemService, UserItemService userItemService) {
        this.userService = userService;
        this.itemService = itemService;
        this.userItemService = userItemService;
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
        List<User> usersWithoutCurrent = userService.getAll();
        usersWithoutCurrent.removeIf(user -> Objects.equals(user.getId(), ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId()));
        model.addAttribute("users", usersWithoutCurrent);
        return "/admin/users/index";
    }

    @GetMapping("/items/add")
    public String addItemPage(@ModelAttribute("item") Item item) {
        return "/admin/items/add";
    }

    @PostMapping("/items/add")
    public String addItem(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/items/add";
        }
        itemService.addItem(item);
        return "redirect:/admin/items";
    }

    @PatchMapping("/items/{id}/edit")
    public String editItem(@PathVariable("id") int id, @ModelAttribute("item") @Valid Item item,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/items/show";
        }
        itemService.update(item, id);
        return "redirect:/admin/items/{id}";
    }

    @DeleteMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable("id") int id) {
        itemService.deleteById(id);
        return "redirect:/admin/items";
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("items", userItemService.getAllItemsInCart(user));
        return "/admin/users/show";
    }

    @PatchMapping("/users/{id}/update-role")
    public String updateRole(@PathVariable("id") int id) {
        User user = userService.getById(id);
        if (user.getRole().equals("ROLE_USER")) {
            user.setRole("ROLE_ADMIN");
        } else if (user.getRole().equals("ROLE_ADMIN")) {
            user.setRole("ROLE_USER");
        }
        userService.update(user, id);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
