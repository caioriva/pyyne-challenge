package com.pyyne.bankmanager.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/banks/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    // TODO transform this into an endpoint
    public void printTransactions() {
        System.out.println("Implement me to pull transactions from all available bank integrations and display them, one after the other.");
    }
}
