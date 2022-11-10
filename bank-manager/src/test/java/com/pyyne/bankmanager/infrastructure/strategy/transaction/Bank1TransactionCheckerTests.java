package com.pyyne.bankmanager.infrastructure.strategy.transaction;

import com.pyyne.bankmanager.infrastructure.bank1.integration.Bank1AccountSource;
import com.pyyne.bankmanager.infrastructure.bank1.integration.Bank1Transaction;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;
import com.pyyne.bankmanager.model.transaction.AccountTransactionType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Bank1TransactionCheckerTests {

    private static final Map<Integer, AccountTransactionType> TRANSACTION_TYPE_MAP = Map.of(
            Bank1Transaction.TYPE_CREDIT, AccountTransactionType.CREDIT,
            Bank1Transaction.TYPE_DEBIT, AccountTransactionType.DEBIT
    );

    @Test
    public void getTransactions_inTheNormalWorkflow_shouldReturnCorrectHardcodedAccountTransactions()
            throws ParseException {
        // Assert
        long expectedExternalAccountId = 123;
        Bank1AccountSource accountSource = new Bank1AccountSource();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedFromDate = formatter.parse("2021-01-01");
        Date expectedToDate = formatter.parse("2022-01-01");

        List<AccountTransaction> expectedTransactions =
                accountSource.getTransactions(expectedExternalAccountId, expectedFromDate, expectedToDate).stream()
                .map(bank2Transaction -> new AccountTransaction(bank2Transaction.getAmount(),
                        TRANSACTION_TYPE_MAP.get(bank2Transaction.getType()),
                        bank2Transaction.getText())).toList();

        // Act
        List<AccountTransaction> actualTransactions = new Bank1TransactionChecker()
                .getTransactions(expectedExternalAccountId, expectedFromDate, expectedToDate);

        // Assert
        assertIterableEquals(expectedTransactions, actualTransactions);
    }
}
