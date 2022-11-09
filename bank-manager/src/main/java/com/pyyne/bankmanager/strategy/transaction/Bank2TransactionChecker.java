package com.pyyne.bankmanager.strategy.transaction;

import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountSource;
import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountTransaction.TRANSACTION_TYPES;
import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransaction;
import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransactionType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class Bank2TransactionChecker implements TransactionCheckerStrategy{

    private static final Map<TRANSACTION_TYPES, AccountTransactionType> TRANSACTION_TYPE_MAP = Map.of(
            TRANSACTION_TYPES.CREDIT, AccountTransactionType.CREDIT,
            TRANSACTION_TYPES.DEBIT, AccountTransactionType.DEBIT
    );

    private final Bank2AccountSource accountSource;

    // Instantiating the class manually for the sake of not change the challenge classes
    public Bank2TransactionChecker() {
        this.accountSource = new Bank2AccountSource();
    }


    // The conversion from TRANSACTION_TYPES to AccountTransactionType should be handled correctly
    // (for the case of not supported types) in a real life situation
    @Override
    public List<AccountTransaction> getTransactions(long externalAccountId, Date fromDate, Date toDate) {

        return accountSource.getTransactions(externalAccountId, fromDate, toDate).stream()
                .map(bank2Transaction -> new AccountTransaction(bank2Transaction.getAmount(),
                        TRANSACTION_TYPE_MAP.get(bank2Transaction.getType()),
                        bank2Transaction.getText())).toList();
    }
}
