package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.UserItemService;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal-info")
public class PersonalInfoController {
    private final UserItemService userItemService;

    @Autowired
    public PersonalInfoController(UserItemService userItemService) {
        this.userItemService = userItemService;
    }

    @GetMapping()
    public String getInfo(Model model) {
        model.addAttribute("user",
                ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        return "/personal-info/show";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCartPage(Model model) {
        model.addAttribute("items",
                userItemService.getAllItemsInCart(((UserDetailsImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()).getUser()));
        return "/personal-info/shopping-cart";
    }
}
