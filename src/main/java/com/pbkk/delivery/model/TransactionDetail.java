package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="transaction_detail")
@Getter @Setter
public class TransactionDetail implements Serializable {

    private @Id @GeneratedValue int id;

    private Food food_item;

    private int amount;

    private Long total;

    private String status;

    @JoinColumn(name="transaction_id")
    private Transaction transaction;
}
