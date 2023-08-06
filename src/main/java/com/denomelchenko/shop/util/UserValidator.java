package com.denomelchenko.shop.util;

import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (foundUser.isPresent()) {
            if (principal instanceof UserDetailsImpl userDetails) {
                if (!Objects.equals(userDetails.getUser().getId(), foundUser.get().getId())) {
                    errors.rejectValue("username", "", "User with this username already exists");
                }
            } else {
                errors.rejectValue("username", "", "User with this username already exists");
            }
        }
    }
}