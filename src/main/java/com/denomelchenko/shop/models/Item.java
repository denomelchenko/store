package com.denomelchenko.shop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Item")
@Getter
@Setter
@NoArgsConstructor
public class Item {
    private static final String NOT_EMPTY_MESSAGE = "cannot be empty";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(max = 50, message = "length should be less than 50")
    private String name;

    @Column(name = "price")
    @NotNull(message = NOT_EMPTY_MESSAGE)
    @DecimalMin(value = "0.0", message = "should be bigger than 0.0")
    private Double price;

    @Column(name = "information")
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    @Size(max = 255, message = "length should be less than 255")
    private String information;

    @ManyToMany
    @JoinTable(name = "userItem", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
