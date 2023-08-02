package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.UserItemService;
import com.denomelchenko.shop.services.UserService;
import com.denomelchenko.shop.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/personal-info")
public class PersonalInfoController {
    private final UserItemService userItemService;
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public PersonalInfoController(UserItemService userItemService,
                                  UserService userService,
                                  UserValidator userValidator) {
        this.userItemService = userItemService;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String getInfo(Model model) {
        model.addAttribute("user",
                ((UserDetailsImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()).getUser());
        return "/personal-info/show";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCartPage(Model model) {
        model.addAttribute("items",
                userItemService.getAllItemsInCart(((UserDetailsImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()).getUser()));
        return "/personal-info/shopping-cart";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "/personal-info/show";
        }
        userService.update(user, ((UserDetailsImpl) SecurityContextHolder.getContext()
                                        .getAuthentication().getPrincipal()).getUser().getId());
        userDetails.updateUser(user);
        return "redirect:/personal-info";
    }
}
