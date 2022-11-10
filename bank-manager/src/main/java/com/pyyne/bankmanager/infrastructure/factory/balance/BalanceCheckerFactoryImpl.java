package com.pyyne.bankmanager.infrastructure.factory.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.strategy.balance.BalanceCheckerStrategy;
import com.pyyne.bankmanager.infrastructure.strategy.balance.Bank1BalanceChecker;
import com.pyyne.bankmanager.infrastructure.strategy.balance.Bank2BalanceChecker;
import com.pyyne.bankmanager.model.BankInstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceCheckerFactoryImpl implements BalanceCheckerFactory {

    private final Bank1BalanceChecker bank1BalanceChecker;

    private final Bank2BalanceChecker bank2BalanceChecker;

    @Autowired
    public BalanceCheckerFactoryImpl(Bank1BalanceChecker bank1BalanceChecker, Bank2BalanceChecker bank2BalanceChecker) {
        this.bank1BalanceChecker = bank1BalanceChecker;
        this.bank2BalanceChecker = bank2BalanceChecker;
    }

    @Override
    public BalanceCheckerStrategy getBalanceChecker(BankInstitution bankInstitution)
            throws BankInstitutionNotSupportedException {
        switch (bankInstitution) {
            case BANK_1:
                return bank1BalanceChecker;
            case BANK_2:
                return bank2BalanceChecker;
        }

        throw new BankInstitutionNotSupportedException(bankInstitution + " is not supported.");
    }
}
