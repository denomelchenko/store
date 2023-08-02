package com.denomelchenko.shop.util;

import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.UserDetailsServiceImpl;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        if (foundUser.isPresent() && !Objects.equals(((UserDetailsImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()).getUser().getId(),
                        foundUser.get().getId()))
            errors.rejectValue("username", "", "User with this username already exist");
    }
}
