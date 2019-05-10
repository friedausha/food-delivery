package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction_detail")
@Getter
@Setter
public class TransactionDetail implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Food food_item;
    private Integer amount;
    private Integer total;
    private String status;
    @ManyToOne
    private Transaction transaction;

    public TransactionDetail(){}
    public TransactionDetail(Transaction transaction,
                             Food food,
                             Integer amount,
                             Integer total,
                             String status){
        this.transaction = transaction;
        this.food_item = food;
        this.amount = amount;
        this.total = total;
        this.status = status;
    }
}
