package com.denomelchenko.shop.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "Item")
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    @DecimalMin("0.0")
    private Double price;

    @Column(name = "information")
    private String information;
}
