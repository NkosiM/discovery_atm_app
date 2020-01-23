package com.discovery.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountReportDto {

    private int clientId;
    private String Surname;
    private String accountNumber;
    private String description;
    private BigDecimal balance;

    public static AccountReportDto mapToAccountReportDto(Object[] object){

        AccountReportDto accountReportDto = new AccountReportDto();
        accountReportDto.setClientId(Integer.parseInt(object[0].toString()));
        accountReportDto.setSurname(object[1].toString());
        accountReportDto.setAccountNumber(object[2].toString());
        accountReportDto.setDescription(object[3].toString());
        accountReportDto.setBalance(new BigDecimal(object[4].toString()));

        return accountReportDto;
    }

}
