package com.pyyne.bankmanager.model.balance;

import com.pyyne.bankmanager.model.AccountCurrency;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalance that = (AccountBalance) o;
        return Objects.equals(amount, that.amount) && currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
