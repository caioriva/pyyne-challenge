package com.pyyne.bankmanager.infrastructure.factory.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.TransactionCheckerStrategy;
import com.pyyne.bankmanager.model.BankInstitution;

public interface TransactionCheckerFactory {

    TransactionCheckerStrategy getTransactionChecker(BankInstitution bankInstitution)
            throws BankInstitutionNotSupported;
}
