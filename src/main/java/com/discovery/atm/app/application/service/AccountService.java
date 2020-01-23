package com.discovery.atm.app.application.service;

import com.discovery.atm.app.domain.*;
import com.discovery.atm.app.domain.dto.*;
import com.discovery.atm.app.exception.GenericException;
import com.discovery.atm.app.exception.ResourceNotFound;
import com.discovery.atm.app.infrastructure.persistence.AtmAllocationRepository;
import com.discovery.atm.app.infrastructure.persistence.ClientAccountRepository;
import com.discovery.atm.app.infrastructure.persistence.ClientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.discovery.atm.app.domain.dto.CurrencyAccountDto.*;
import static java.math.RoundingMode.HALF_UP;

@Service
public class AccountService {

    private ClientAccountRepository clientAccountRepository;

    private AtmAllocationRepository atmAllocationRepository;


    public AccountService(final ClientAccountRepository clientAccountRepository,
                          final AtmAllocationRepository atmAllocationRepository) {
        this.clientAccountRepository = clientAccountRepository;
        this.atmAllocationRepository = atmAllocationRepository;
    }

    public List<AccountDto> getAccountBalances(Integer id, boolean transactional) throws ResourceNotFound {

        List<Object[]> clientAccounts = clientAccountRepository.findAllByClientId(id, transactional);

        if (clientAccounts.size() == 0) {
            throw new ResourceNotFound();
        }
        return clientAccounts.stream()
                .map(AccountDto::mapToAccountDto)
                .collect(Collectors.toList());
    }


    public List<CurrencyAccountDto> getCurrencyAccounts(Integer id, boolean transactional) throws ResourceNotFound {
        List<Object[]> currencyAccounts = clientAccountRepository.findAllCurrencyAccountsByClientId(id, transactional);

        if (currencyAccounts.size() == 0) {
            throw new ResourceNotFound();
        }
        List<CurrencyAccountDto> responseList = new ArrayList<>();
        currencyAccounts.forEach(obj ->{
           BigDecimal convertedResult = convertToZAR(obj[3].toString(), new BigDecimal(obj[1].toString()), new BigDecimal(obj[4].toString()));
           responseList.add(mapToDto(obj, convertedResult));

        });

        //TODO: Sort list by zar amount
        return responseList;
    }

    public  List<AtmDenominationResponseDto> withdrawFromAcc(Integer id, AccountWithdrawalRequestDto accountWithdrawalRequestDto) throws GenericException {

        List<AtmDenominationResponseDto> atmDenominationResponseDtoList;
        BigDecimal amountAfterWithdrawal;

         //Get Client account details
        ClientAccount clientAccountDetails =  clientAccountRepository. findByClientAccountNumber(accountWithdrawalRequestDto.getAccountNumber());

        // Check the type of account it is
        if(clientAccountDetails.getAccount_type_code().getAccount_type_code().equals(AccountTypeEnum.CHQ.toString())){

            //Determine negative balance if the account is a cheque account up to a maximum of R 10 000
            int i = clientAccountDetails.getDisplayBalance().subtract(accountWithdrawalRequestDto.getWithdrawalAmt()).intValue();
            if(clientAccountDetails.getDisplayBalance().compareTo(new BigDecimal(-10000)) == -1||i < -10000){
                throw new GenericException("Insufficient funds: You have exceeded your cheque account limit");
            }


            atmDenominationResponseDtoList = getAtmDispensation(accountWithdrawalRequestDto.getWithdrawalAmt(), accountWithdrawalRequestDto.getAtmId());

            amountAfterWithdrawal = clientAccountDetails.getDisplayBalance().subtract(accountWithdrawalRequestDto.getWithdrawalAmt());
            updateClientAccountBalance(amountAfterWithdrawal,clientAccountDetails.getClientAccountNumber());

        }else{
            //Determine current balance : if client acc balance is greater than available amount in the bank
            if(clientAccountDetails.getDisplayBalance().compareTo(accountWithdrawalRequestDto.getWithdrawalAmt()) == -1){
                throw new GenericException("Insufficient funds");
            }
            atmDenominationResponseDtoList = getAtmDispensation(accountWithdrawalRequestDto.getWithdrawalAmt(), accountWithdrawalRequestDto.getAtmId());

            amountAfterWithdrawal = clientAccountDetails.getDisplayBalance().subtract(accountWithdrawalRequestDto.getWithdrawalAmt());
            updateClientAccountBalance(amountAfterWithdrawal,clientAccountDetails.getClientAccountNumber());
        }
        return atmDenominationResponseDtoList;
    }

    private List<AtmDenominationResponseDto> getAtmDispensation(BigDecimal requestAmt, Integer atmId) throws GenericException {

        //Retrieve ATM allocation
        List<Object[]> atmAllocations = atmAllocationRepository.getListOfDenominationsAndSumByAtmId(atmId);

        List<AtmAllocationModel> atmAllocationModelList = atmAllocations.stream()
                .map(AtmAllocationModel::mapToAtmAllocationModel)
                .collect(Collectors.toList());

        // Tally
        int atmAllocationSum = 0;
        for (AtmAllocationModel atmAllocation : atmAllocationModelList) {
            atmAllocationSum += atmAllocationSum + atmAllocation.getDenominationSum();
        }

        if(requestAmt.compareTo(new BigDecimal(atmAllocationSum)) == 1){
            throw new GenericException("Amount not available, would you like to draw R "+atmAllocationSum);
        }

        //
        List<AtmDenominationResponseDto> atmDenominationResponseDtoList = new ArrayList<>();
        int countNotes = 0;
        BigDecimal tempDispenseBalance = requestAmt;
        for(AtmAllocationModel model: atmAllocationModelList){
            if(model.getCount() > 0) {

                if (tempDispenseBalance.remainder(new BigDecimal(model.getValue())).compareTo(BigDecimal.ZERO) == 0) {

                    countNotes = tempDispenseBalance.divide(new BigDecimal(model.getValue())).intValue(); //7
                    //check available notes
                    if (model.getCount() >= countNotes) {
                        model.setCount(model.getCount()-countNotes);
                        //update response obj
                        atmDenominationResponseDtoList.add(new AtmDenominationResponseDto(model.getValue(),countNotes));
                        tempDispenseBalance = BigDecimal.ZERO;
                        break;

                    } else {
                        //update response obj to tell it how many notes were used
                        atmDenominationResponseDtoList.add(new AtmDenominationResponseDto(model.getValue(),model.getCount()));
                        //use all notes
                        model.setCount(0);

                        tempDispenseBalance = tempDispenseBalance.subtract(new BigDecimal(model.getDenominationSum()));
                    }

                }else if (tempDispenseBalance.divide(new BigDecimal(model.getValue())).intValue() >=1 ){
                    //when there is a remainder

                    countNotes = tempDispenseBalance.divide(new BigDecimal(model.getValue())).intValue();
                    //update response obj to tell it how many notes were used
                    atmDenominationResponseDtoList.add(new AtmDenominationResponseDto(model.getValue(),countNotes));
                    //use all notes
                    model.setCount(model.getCount() - countNotes);

                    tempDispenseBalance = tempDispenseBalance.subtract(new BigDecimal(countNotes*model.getValue()));
                }
            }
        }



        return atmDenominationResponseDtoList;
    }

    private void updateClientAccountBalance(BigDecimal amountAfterWithdrawal, String clientAccNum ){

        clientAccountRepository.updateClientAccountBalance(amountAfterWithdrawal,clientAccNum);

    }


    private BigDecimal convertToZAR(String convIndicator, BigDecimal curBalance, BigDecimal convRate){

        /***************************************************************************************
         * Currency conversion rats                                                            *
         * The CONVERSION_INDICATOR specifies if the foreign amount should be multiplied ('*') *
         * or divided when converting to the local amount.                                     *
         *                                                                                     *
         *  i.e. USD 12 = 12 * 11.6167                                                         *
         *              = 139.40 ZAR                                                           *
         *                                                                                     *
         *                                                                                     *
         *       AUD 12 = 12 / 0.1134                                                          *
         *              = 105.82 ZAR                                                           *
         ***************************************************************************************/

        switch (convIndicator){
            case "*" : return curBalance.multiply(convRate);
            case "/" : return curBalance.divide(convRate, HALF_UP);
            default : return new BigDecimal(0);
        }
    }
}
