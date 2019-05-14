package com.pbkk.delivery.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
@Data
public class Restaurant {
    @Id
    @GeneratedValue
    private Integer id;
}
