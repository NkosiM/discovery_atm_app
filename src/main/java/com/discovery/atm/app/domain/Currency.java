package com.discovery.atm.app.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currency")
@Data
public class Currency implements Serializable {

    @Id
    @Column(name = "currency_code")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String currencyCode;

    @Column(name = "decimal_places")
    private int decimalPlaces;

    @Column(name = "description")
    private String description;
}

