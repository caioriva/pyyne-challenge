package com.pyyne.bankmanager.factory.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.bank.BankInstitution;
import com.pyyne.bankmanager.strategy.balance.BalanceCheckerStrategy;
import com.pyyne.bankmanager.strategy.balance.Bank1BalanceChecker;
import com.pyyne.bankmanager.strategy.balance.Bank2BalanceChecker;
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
            throws BankInstitutionNotSupported {
        switch (bankInstitution) {
            case BANK_1:
                return bank1BalanceChecker;
            case BANK_2:
                return bank2BalanceChecker;
        }

        throw new BankInstitutionNotSupported(bankInstitution + " is not supported.");
    }
}
