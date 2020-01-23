package com.discovery.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {
    private String accountNumber;
    private String accountType;
    private BigDecimal accountBalance;

    public static AccountDto mapToAccountDto(Object[] object){

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(object[0].toString());
        accountDto.setAccountType(object[1].toString());
        accountDto.setAccountBalance(new BigDecimal(object[2].toString()));

        return accountDto;
    }
}
