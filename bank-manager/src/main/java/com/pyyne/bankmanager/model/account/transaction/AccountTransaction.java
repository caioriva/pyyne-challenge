package com.pyyne.bankmanager.model.account.transaction;

public class AccountTransaction {

    private double amount;

    private AccountTransactionType type;

    private String description;

    public AccountTransaction(double amount, AccountTransactionType type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public AccountTransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
