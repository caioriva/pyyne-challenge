package com.pyyne.bankmanager.factory.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.bank.BankInstitution;
import com.pyyne.bankmanager.strategy.balance.BalanceCheckerStrategy;

public interface BalanceCheckerFactory {

    BalanceCheckerStrategy getBalanceChecker(BankInstitution bankInstitution) throws BankInstitutionNotSupported;
}
