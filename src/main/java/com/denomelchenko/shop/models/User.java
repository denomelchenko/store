package com.denomelchenko.shop.models;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    private String firstName;

//    private String lastName;

    //private String dateOfBirth;

    @Size(min = 2, max = 100, message = "username should be between 2 and 100")
    private String username;

    @Size(min = 8, message = "password should be bigger than 8 chars")
    private String password;

    private String role;
}
