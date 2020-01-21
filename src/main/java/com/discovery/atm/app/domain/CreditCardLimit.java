package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "credit_card_limit")
@Data
public class CreditCardLimit implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NonNull
    private ClientAccount clientAccountNumber;

    @NonNull
    @Column(name = "account_limit")
    private double accountLimit;
}
