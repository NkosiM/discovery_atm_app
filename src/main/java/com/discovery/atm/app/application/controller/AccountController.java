package com.discovery.atm.app.application.controller;

import com.discovery.atm.app.application.service.AccountService;
import com.discovery.atm.app.domain.dto.AccountDto;
import com.discovery.atm.app.domain.dto.AccountWithdrawalRequestDto;
import com.discovery.atm.app.domain.dto.AtmDenominationResponseDto;
import com.discovery.atm.app.domain.dto.CurrencyAccountDto;
import com.discovery.atm.app.exception.GenericException;
import com.discovery.atm.app.exception.ResourceNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clients")
@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}/accounts")
    public List<AccountDto> getAccountBalance(@PathVariable Integer id, @RequestParam boolean transactional) throws ResourceNotFound {

        return accountService.getAccountBalances(id, transactional);
    }

    @GetMapping("/{id}/currencyAccount")
    public List<CurrencyAccountDto> getCurrencyAccounts(@PathVariable Integer id, @RequestParam boolean transactional) throws ResourceNotFound {

        return accountService.getCurrencyAccounts(id, transactional);
    }

    @GetMapping("/{id}/withdrawFromAccount")
    public List<AtmDenominationResponseDto> withdrawFromAcc(@PathVariable Integer id, final AccountWithdrawalRequestDto accountWithdrawalRequestDto) throws  GenericException {

        return accountService.withdrawFromAcc(id, accountWithdrawalRequestDto);
    }


}
