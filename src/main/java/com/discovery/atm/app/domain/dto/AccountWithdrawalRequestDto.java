package com.discovery.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AccountWithdrawalRequestDto {

    private String accountNumber;
    private String accountType;
    private BigDecimal withdrawalAmt;
    private int atmId;
    private int clientId;
}
