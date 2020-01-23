package com.discovery.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyAccountDto {

    private String accountNumber;
    private String currency;
    private BigDecimal currencyBalance;
    private BigDecimal conversionRate;
    private BigDecimal zarAmount;


    public static CurrencyAccountDto mapToDto(Object[] object, BigDecimal zarAmount) {
        CurrencyAccountDto currencyAccountDto = new CurrencyAccountDto();
        currencyAccountDto.setAccountNumber(object[0].toString());
        currencyAccountDto.setCurrency(object[2].toString());
        currencyAccountDto.setCurrencyBalance(new BigDecimal(object[1].toString()));
        currencyAccountDto.setConversionRate(new BigDecimal(object[4].toString()));
        currencyAccountDto.setZarAmount(zarAmount);
        return currencyAccountDto;
    }
}
