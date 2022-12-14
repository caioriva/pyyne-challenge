package com.pyyne.bankmanager.infrastructure.factory.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.strategy.balance.BalanceCheckerStrategy;
import com.pyyne.bankmanager.model.BankInstitution;

public interface BalanceCheckerFactory {

    BalanceCheckerStrategy getBalanceChecker(BankInstitution bankInstitution) throws BankInstitutionNotSupportedException;
}
