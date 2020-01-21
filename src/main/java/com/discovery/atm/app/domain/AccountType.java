package com.discovery.atm.app.domain;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_type")
@Data
public class AccountType implements Serializable {


    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_type_code")
    private String accountTypeCode;

    @NonNull
    @Column(name = "description")
    private String description;

    @Column(name = "transactional")
    private boolean transactional;

}
