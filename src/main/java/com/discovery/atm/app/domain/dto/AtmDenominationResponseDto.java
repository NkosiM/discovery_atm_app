package com.discovery.atm.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AtmDenominationResponseDto {

    private int key;
    private int count;


}
