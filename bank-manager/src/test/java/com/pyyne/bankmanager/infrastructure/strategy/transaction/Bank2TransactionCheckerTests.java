package com.pyyne.bankmanager.infrastructure.strategy.transaction;

import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountSource;
import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountTransaction.TRANSACTION_TYPES;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;
import com.pyyne.bankmanager.model.transaction.AccountTransactionType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Bank2TransactionCheckerTests {

    private static final Map<TRANSACTION_TYPES, AccountTransactionType> TRANSACTION_TYPE_MAP = Map.of(
            TRANSACTION_TYPES.CREDIT, AccountTransactionType.CREDIT,
            TRANSACTION_TYPES.DEBIT, AccountTransactionType.DEBIT
    );

    @Test
    public void getTransactions_inTheNormalWorkflow_shouldReturnCorrectHardcodedAccountTransactions()
            throws ParseException {
        // Assert
        long expectedExternalAccountId = 123;
        Bank2AccountSource accountSource = new Bank2AccountSource();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedFromDate = formatter.parse("2021-01-01");
        Date expectedToDate = formatter.parse("2022-01-01");

        List<AccountTransaction> expectedTransactions =
                accountSource.getTransactions(expectedExternalAccountId, expectedFromDate, expectedToDate).stream()
                        .map(bank2Transaction -> new AccountTransaction(bank2Transaction.getAmount(),
                                TRANSACTION_TYPE_MAP.get(bank2Transaction.getType()),
                                bank2Transaction.getText())).toList();

        // Act
        List<AccountTransaction> actualTransactions = new Bank2TransactionChecker()
                .getTransactions(expectedExternalAccountId, expectedFromDate, expectedToDate);

        // Assert
        assertIterableEquals(expectedTransactions, actualTransactions);
    }
}
