package com.pbkk.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String phone_number;
    private String password;
    private Integer role;

    public User(String username, String phone_number, String password, int role) {
        this.username = username;
        this.phone_number = phone_number;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
}
