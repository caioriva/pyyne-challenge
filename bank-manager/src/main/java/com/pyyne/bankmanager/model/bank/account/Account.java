package com.pyyne.bankmanager.model.bank.account;

import com.pyyne.bankmanager.model.bank.Bank;

public class Account {

    private long internalId;

    private long externalId;

    private Bank associatedBank;

    public Account(long internalId, long externalId, Bank associatedBank) {
        this.internalId = internalId;
        this.externalId = externalId;
        this.associatedBank = associatedBank;
    }

    public long getInternalId() {
        return internalId;
    }

    public long getExternalId() {
        return externalId;
    }

    public Bank getAssociatedBank() {
        return associatedBank;
    }
}
