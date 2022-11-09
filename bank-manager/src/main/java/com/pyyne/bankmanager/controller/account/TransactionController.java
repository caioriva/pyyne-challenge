package com.pyyne.bankmanager.controller.account;

import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransaction;
import com.pyyne.bankmanager.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts/{accountId}/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AccountTransaction> getTransactions(
            @PathVariable long accountId, @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date toDate) {
        System.out.println(String.format("accountId:%s, from:%s, to:%s", accountId, fromDate, toDate));
        return null;
    }
}
