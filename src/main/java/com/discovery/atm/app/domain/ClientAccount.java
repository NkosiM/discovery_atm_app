package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_account")
@Data
public class ClientAccount implements Serializable {

    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_account_number")
    private String clientAccountNumber;

    @Column(name = "display_balance")
    private double displayBalance;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client clientId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_type_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AccountType accountTypeCode;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Currency currencyCode;


}
