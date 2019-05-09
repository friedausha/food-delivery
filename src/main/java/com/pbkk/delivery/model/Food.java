package com.pbkk.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="food")
@Getter @Setter
public class Food implements Serializable {

    private @Id @GeneratedValue int id;

    private String name;

    private Long price;

    @Lob
    private byte[] picture;
}
