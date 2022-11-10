package com.pyyne.bankmanager.model.balance;

import com.pyyne.bankmanager.model.AccountCurrency;

public class AccountBalance {

    public Double amount;

    public AccountCurrency currency;

    public AccountBalance(Double amount, AccountCurrency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }
}
