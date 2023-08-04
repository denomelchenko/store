package com.denomelchenko.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private static final String NOT_EMPTY_MESSAGE = "cannot be empty";

    @Column(name = "id")
    private Integer id;

    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 2, max = 50, message = "first name length should be between 2 and 50 chars")
    private String firstName;

    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 2, max = 50, message = "last name length should be between 2 and 50 chars")
    private String lastName;

    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 2, max = 100, message = "username length should be between 2 and 100 chars")
    private String username;
}
