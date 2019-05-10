package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private User customer;
    @ManyToOne
    private User driver;

    public Transaction(User customer, User driver) {
        this.customer = customer;
        this.driver = driver;
    }
    public Transaction(){}
}
