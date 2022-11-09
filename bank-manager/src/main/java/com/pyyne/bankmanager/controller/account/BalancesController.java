package com.pyyne.bankmanager.controller.account;

import com.pyyne.bankmanager.model.account.balance.AccountBalance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts/{accountId}/balances",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class BalancesController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountBalance> getBalances(@PathVariable long accountId) {
        System.out.println(String.format("accountId:%s", accountId));
        return null;
    }
}
