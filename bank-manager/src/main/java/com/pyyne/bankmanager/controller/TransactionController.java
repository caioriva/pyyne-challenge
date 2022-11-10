package com.pyyne.bankmanager.controller;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;
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

    // BankInstitutionNotSupported should be handled correctly with Spring ExceptionHandler in a real life situation
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AccountTransaction> getTransactions(
            @PathVariable long accountId, @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date toDate) throws BankInstitutionNotSupported {

        return transactionService.getTransactions(accountId, fromDate, toDate);
    }
}
