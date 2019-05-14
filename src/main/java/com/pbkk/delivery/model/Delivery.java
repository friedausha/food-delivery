package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name="delivery")
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Driver driver;

    private Integer status;

    @NotEmpty(message = "delivery address can't be null")
    private String delivery_address;

    /**
     * 1 - Order created
     * 2 - Order accepted
     * 3 - No driver accepted order
     * 4 - Driver on the way to restaurant
     * 5 - Driver arrived at restaurant
     * 6 - Driver on the way to customer
     * 7 - Driver arrived on customer's place
     * 8 - Order delivered
     */

    public Delivery(){}
}
