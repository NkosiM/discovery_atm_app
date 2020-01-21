package com.discovery.atm.app.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;



}
