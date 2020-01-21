package com.discovery.atm.app.domain;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "atm")
@Data
public class ATM implements Serializable {

    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "location")
    private String location;
}
