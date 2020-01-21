package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "client")
@Data
public class Client implements Serializable {


    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String name;
    private String surname;
    private Date dob;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_sub_type_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClientSubType clientSubTypeCode;




}
