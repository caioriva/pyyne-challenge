package com.pyyne.bankmanager.model.account;

import com.pyyne.bankmanager.model.BankInstitution;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return internalId == account.internalId && externalId == account.externalId
                && associatedBankInstitution == account.associatedBankInstitution;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, externalId, associatedBankInstitution);
    }
}
