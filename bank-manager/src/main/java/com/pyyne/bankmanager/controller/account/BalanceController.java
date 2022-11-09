package com.pyyne.bankmanager.controller.account;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;
import com.pyyne.bankmanager.service.balance.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts/{accountId}/balances",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    // BankInstitutionNotSupported should be handled correctly with Spring ExceptionHandler in a real life situation
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountBalance> getBalances(@PathVariable long accountId) throws BankInstitutionNotSupported {

        return balanceService.getAvailableBalances(accountId);
    }
}
