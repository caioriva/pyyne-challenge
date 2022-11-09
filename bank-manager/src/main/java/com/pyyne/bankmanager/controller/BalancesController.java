package com.pyyne.bankmanager.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/banks/balances",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class BalancesController {

    // TODO transform this into an endpoint
    public void printBalances() {
        System.out.println("Implement me to pull balance information from all available bank integrations and display them, one after the other.");
    }
}
