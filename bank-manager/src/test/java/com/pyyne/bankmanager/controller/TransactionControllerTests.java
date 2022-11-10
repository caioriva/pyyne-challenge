package com.pyyne.bankmanager.controller;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;
import com.pyyne.bankmanager.model.transaction.AccountTransactionType;
import com.pyyne.bankmanager.service.transaction.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class TransactionControllerTests {

    private TransactionService transactionService;
    private TransactionController transactionController;

    @Before
    public void setUp() {
        transactionService = mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    public void getTransactions_inTheNormalWorkflow_shouldReturnCorrectTransactionList()
            throws BankInstitutionNotSupportedException, ParseException {
        // Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long expectedAccountId = 123;
        Date expectedFromDate = formatter.parse("2021-01-01");
        Date expectedToDate = formatter.parse("2022-01-01");
        List<AccountTransaction> expectedTransactions = Collections.singletonList(
                new AccountTransaction(120d, AccountTransactionType.DEBIT, "test"));

        when(transactionService.getTransactions(expectedAccountId, expectedFromDate, expectedToDate))
                .thenReturn(expectedTransactions);

        // Act
        List<AccountTransaction> actualTransactions =
                transactionController.getTransactions(expectedAccountId, expectedFromDate, expectedToDate);

        // Assert
        verify(transactionService, times(1))
                .getTransactions(expectedAccountId, expectedFromDate, expectedToDate);
        assertIterableEquals(expectedTransactions, actualTransactions);
    }
}
