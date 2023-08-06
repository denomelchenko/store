package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.dto.UserDTO;
import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.UserItemService;
import com.denomelchenko.shop.services.UserService;
import com.denomelchenko.shop.util.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;

    @Autowired
    public PersonalInfoController(UserItemService userItemService, UserService userService, ModelMapper modelMapper, UserValidator userValidator) {
        this.userItemService = userItemService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String getInfo(Model model) {
        model.addAttribute("user", convertToUserDTO(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()));
        return "/personal-info/show";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCartPage(Model model) {
        model.addAttribute("items", userItemService.getAllItemsInCart(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()));
        return "/personal-info/shopping-cart";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user = raiseUp(convertToUser(userDTO), currentUser);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/personal-info/show";
        }
        userService.update(user, currentUser.getId());
        userDetails.updateUser(user);
        return "redirect:/personal-info";
    }

    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private User raiseUp(User userToRaise, User user) {
        userToRaise.setRole(user.getRole());
        userToRaise.setPassword(user.getPassword());
        return userToRaise;
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
