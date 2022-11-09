package com.pyyne.bankmanager.factory;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.bank.BankInstitution;
import com.pyyne.bankmanager.strategy.BalanceCheckerStrategy;

public interface BalanceCheckerFactory {

    BalanceCheckerStrategy getBalanceChecker(BankInstitution bankInstitution) throws BankInstitutionNotSupported;
}
