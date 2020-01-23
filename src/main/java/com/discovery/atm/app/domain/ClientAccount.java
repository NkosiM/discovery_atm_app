package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "client_account")
@Getter
@Setter
@NoArgsConstructor
public class ClientAccount implements Serializable {


    @Id
    @NonNull
    @Column(name = "client_account_number")
    private String clientAccountNumber;

    @Column(name = "display_balance")
    private BigDecimal displayBalance;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id",  referencedColumnName = "client_id" )
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client_id;

    @JoinColumn(name = "account_type_code", referencedColumnName = "account_type_code")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AccountType account_type_code;

    @JoinColumn(name = "currency_code", referencedColumnName = "currency_code")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Currency currency_code;


}
