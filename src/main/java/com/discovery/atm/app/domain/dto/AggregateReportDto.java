package com.discovery.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AggregateReportDto {

    private String clientTitle;
    private String clientName;
    private String clientSurname;
    private BigDecimal loanAccBalance;
    private BigDecimal transactionalAccBalance;
    private BigDecimal netPosition;


    public static AggregateReportDto mapToDto(Object[] object, BigDecimal zarAmount) {
        AggregateReportDto currencyAccountDto = new AggregateReportDto();
//        currencyAccountDto.setAccountNumber(object[0].toString());
//        currencyAccountDto.setCurrency(object[2].toString());
//        currencyAccountDto.setCurrencyBalance(new BigDecimal(object[1].toString()));
//        currencyAccountDto.setConversionRate(new BigDecimal(object[4].toString()));
//        currencyAccountDto.setZarAmount(zarAmount);
        return currencyAccountDto;
    }
}
