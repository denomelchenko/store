package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal-info")
public class PersonalInfoController {
    @GetMapping()
    public String getInfo(Model model) {
        model.addAttribute("user", ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        return "/personal-info/show";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCartPage(Model model) {
        return "/personal-info/shopping-cart";
    }
}
