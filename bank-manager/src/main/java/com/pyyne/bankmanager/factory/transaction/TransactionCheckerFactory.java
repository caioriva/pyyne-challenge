package com.pyyne.bankmanager.factory.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.bank.BankInstitution;
import com.pyyne.bankmanager.strategy.balance.BalanceCheckerStrategy;
import com.pyyne.bankmanager.strategy.transaction.TransactionCheckerStrategy;

public interface TransactionCheckerFactory {

    TransactionCheckerStrategy getTransactionChecker(BankInstitution bankInstitution)
            throws BankInstitutionNotSupported;
}
