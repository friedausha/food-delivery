package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="transaction")
@Getter @Setter
public class Transaction implements Serializable {

    private @Id @GeneratedValue int id;

    @JoinColumn(name="customer_id")
    private User customer;

    @JoinColumn(name="driver_id")
    private User driver;

    public Transaction(){}
}
