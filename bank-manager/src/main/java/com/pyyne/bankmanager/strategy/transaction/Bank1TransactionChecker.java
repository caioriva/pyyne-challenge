package com.pyyne.bankmanager.strategy.transaction;

import com.pyyne.bankmanager.infrastructure.bank1.integration.Bank1AccountSource;
import com.pyyne.bankmanager.infrastructure.bank1.integration.Bank1Transaction;
import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransaction;
import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransactionType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class Bank1TransactionChecker implements TransactionCheckerStrategy {

    private static final Map<Integer, AccountTransactionType> TRANSACTION_TYPE_MAP = Map.of(
            Bank1Transaction.TYPE_CREDIT, AccountTransactionType.CREDIT,
            Bank1Transaction.TYPE_DEBIT, AccountTransactionType.DEBIT
    );

    private final Bank1AccountSource accountSource;

    // Instantiating the class manually for the sake of not change the challenge classes
    public Bank1TransactionChecker() {
        this.accountSource = new Bank1AccountSource();
    }

    // The conversion from the transaction type from Bank1Transaction to AccountTransactionType should be handled
    // correctly (for the case of not supported types) in a real life situation
    @Override
    public List<AccountTransaction> getTransactions(long externalAccountId, Date fromDate, Date toDate) {
        return accountSource.getTransactions(externalAccountId, fromDate, toDate).stream()
                .map(bank2Transaction -> new AccountTransaction(bank2Transaction.getAmount(),
                        TRANSACTION_TYPE_MAP.get(bank2Transaction.getType()),
                        bank2Transaction.getText())).toList();
    }
}
