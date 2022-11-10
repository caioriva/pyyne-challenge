package com.pyyne.bankmanager.infrastructure.factory.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.Bank1TransactionChecker;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.Bank2TransactionChecker;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.TransactionCheckerStrategy;
import com.pyyne.bankmanager.model.BankInstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionCheckerFactoryImpl implements TransactionCheckerFactory {

    private final Bank1TransactionChecker bank1TransactionChecker;

    private final Bank2TransactionChecker bank2TransactionChecker;

    @Autowired
    public TransactionCheckerFactoryImpl(Bank1TransactionChecker bank1TransactionChecker,
                                         Bank2TransactionChecker bank2TransactionChecker) {
        this.bank1TransactionChecker = bank1TransactionChecker;
        this.bank2TransactionChecker = bank2TransactionChecker;
    }

    @Override
    public TransactionCheckerStrategy getTransactionChecker(BankInstitution bankInstitution)
            throws BankInstitutionNotSupported {
        switch (bankInstitution) {
            case BANK_1:
                return bank1TransactionChecker;
            case BANK_2:
                return bank2TransactionChecker;
        }

        throw new BankInstitutionNotSupported(bankInstitution + " is not supported.");
    }
}
