package com.discovery.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AtmAllocationModel {

    private int atmAllocationId;
    private int AtmId;
    private int denominationId;
    private int count;
    private int value;
    private int denominationSum;


    public static AtmAllocationModel mapToAtmAllocationModel(Object[] object){

        AtmAllocationModel atmAllocationModel = new AtmAllocationModel();
        atmAllocationModel.setAtmAllocationId(Integer.parseInt(object[0].toString()));
        atmAllocationModel.setAtmId(Integer.parseInt(object[1].toString()));
        atmAllocationModel.setDenominationId(Integer.parseInt(object[2].toString()));
        atmAllocationModel.setCount(Integer.parseInt(object[3].toString()));
        atmAllocationModel.setValue(Integer.parseInt(object[4].toString()));
        atmAllocationModel.setDenominationSum(Integer.parseInt(object[5].toString()));

        return atmAllocationModel;
    }
}
