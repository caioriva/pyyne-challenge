package com.pyyne.bankmanager.infrastructure.factory.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.TransactionCheckerStrategy;
import com.pyyne.bankmanager.model.BankInstitution;

public interface TransactionCheckerFactory {

    TransactionCheckerStrategy getTransactionChecker(BankInstitution bankInstitution)
            throws BankInstitutionNotSupportedException;
}
