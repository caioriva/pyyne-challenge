package com.pyyne.bankmanager.controller.account;

import com.pyyne.bankmanager.model.account.transaction.AccountTransaction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts/{accountId}/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionsController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AccountTransaction> getTransactions(
            @PathVariable long accountId, @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date toDate) {
        System.out.println(String.format("accountId:%s, from:%s, to:%s", accountId, fromDate, toDate));
        return null;
    }
}
