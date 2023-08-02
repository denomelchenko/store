package com.denomelchenko.shop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {
    private static final String NOT_EMPTY_MESSAGE = "cannot be empty";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 2, max = 50, message = "first name length should be between 2 and 50 chars")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 2, max = 50, message = "last name length should be between 2 and 50 chars")
    private String lastName;

    @Column(name = "username")
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 2, max = 100, message = "username length should be between 2 and 100 chars")
    private String username;

    @Column(name = "password")
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(min = 8, message = "password should be bigger than 8 chars")
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Item> items;
}
