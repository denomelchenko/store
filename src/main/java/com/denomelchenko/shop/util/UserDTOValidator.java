package com.denomelchenko.shop.util;

import com.denomelchenko.shop.dto.UserDTO;
import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserDTOValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        if (foundUser.isPresent() && !Objects.equals(user.getId(), foundUser.get().getId()))
            errors.rejectValue("username", "", "User with this username already exist");
    }
}
