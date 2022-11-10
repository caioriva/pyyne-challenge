package com.pyyne.bankmanager.model.account;

import com.pyyne.bankmanager.model.BankInstitution;

public class Account {

    private long internalId;

    private long externalId;

    private BankInstitution associatedBankInstitution;

    public Account(long internalId, long externalId, BankInstitution associatedBankInstitution) {
        this.internalId = internalId;
        this.externalId = externalId;
        this.associatedBankInstitution = associatedBankInstitution;
    }

    public long getInternalId() {
        return internalId;
    }

    public long getExternalId() {
        return externalId;
    }

    public BankInstitution getAssociatedBank() {
        return associatedBankInstitution;
    }
}
