package com.denomelchenko.shop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotBlank
    @Size(min = 2, max = 50, message = "First name length should be between 2 and 50 chars")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Last name length should be between 2 and 50 chars")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "username")
    @Size(min = 2, max = 100, message = "Username length should be between 2 and 100 chars")
    private String username;

    @NotBlank
    @Column(name = "password")
    @Size(min = 8, message = "password should be bigger than 8 chars")
    private String password;

    @Column(name = "role")
    private String role;
}
