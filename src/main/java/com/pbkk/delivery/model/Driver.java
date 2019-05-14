package com.pbkk.delivery.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driver")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Driver {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String phone_number;
    private String address;

    private Double latitude;
    private Double longitude;
    public Driver(){}
}
