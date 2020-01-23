package com.discovery.atm.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_type")
@Getter
@Setter
@NoArgsConstructor
public class AccountType implements Serializable {


    @Id
    @NonNull
    @Column(name = "account_type_code")
    private String account_type_code;

    @NonNull
    @Column(name = "description")
    private String description;

    @Column(name = "transactional")
    private boolean transactional;

}
