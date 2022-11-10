package com.pyyne.bankmanager.model.transaction;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Double.compare(that.amount, amount) == 0 && type == that.type
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, type, description);
    }
}
