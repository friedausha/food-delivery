package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "food")
@Getter
@Setter
public class Food implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Long price;

    @Lob
    private byte[] picture;

    public Food(){}
}
