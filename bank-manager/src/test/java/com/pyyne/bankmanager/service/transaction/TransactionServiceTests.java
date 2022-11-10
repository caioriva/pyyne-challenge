package com.pyyne.bankmanager.service.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.factory.transaction.TransactionCheckerFactory;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.Bank1TransactionChecker;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.Bank2TransactionChecker;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.TransactionCheckerStrategy;
import com.pyyne.bankmanager.model.BankInstitution;
import com.pyyne.bankmanager.model.account.Account;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;
import com.pyyne.bankmanager.model.transaction.AccountTransactionType;
import com.pyyne.bankmanager.service.account.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.pyyne.bankmanager.model.BankInstitution.BANK_1;
import static com.pyyne.bankmanager.model.BankInstitution.BANK_2;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTests {
    private AccountService accountService;
    private TransactionCheckerFactory transactionCheckerFactory;
    private TransactionCheckerStrategy bank1TransactionCheckerStrategy;
    private TransactionCheckerStrategy bank2TransactionCheckerStrategy;
    private TransactionService transactionService;

    @Before
    public void setUp() {
        accountService = mock(AccountService.class);
        transactionCheckerFactory = mock(TransactionCheckerFactory.class);
        bank1TransactionCheckerStrategy = mock(Bank1TransactionChecker.class);
        bank2TransactionCheckerStrategy = mock(Bank2TransactionChecker.class);
        transactionService = new TransactionServiceImpl(accountService, transactionCheckerFactory);
    }

    @Test
    public void getTransactions_inTheNormalWorkflow_shouldReturnCorrectTransactionList()
            throws BankInstitutionNotSupportedException, ParseException {
        // Arrange
        long expectedInternalAccountId = 123;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedFromDate = formatter.parse("2021-01-01");
        Date expectedToDate = formatter.parse("2022-01-01");
        Account expectedBank1Account = new Account(expectedInternalAccountId, 123456, BANK_1);
        Account expectedBank2Account = new Account(expectedInternalAccountId, 654321, BANK_2);
        AccountTransaction expectedBank1Transaction =
                new AccountTransaction(100.0, AccountTransactionType.CREDIT, "Check deposit");
        AccountTransaction expectedBank2Transaction =
                new AccountTransaction(25.5, AccountTransactionType.DEBIT, "Debit card purchase");
        List<Account> expectedAccounts = Arrays.asList(expectedBank1Account, expectedBank2Account);
        List<AccountTransaction> expectedTransactions =
                Arrays.asList(expectedBank1Transaction, expectedBank2Transaction);

        when(accountService.getAssociatedBankAccounts(expectedInternalAccountId)).thenReturn(expectedAccounts);
        when(bank1TransactionCheckerStrategy.getTransactions(expectedBank1Account.getExternalId(),
                expectedFromDate, expectedToDate)).thenReturn(Collections.singletonList(expectedBank1Transaction));
        when(bank2TransactionCheckerStrategy.getTransactions(expectedBank2Account.getExternalId(),
                expectedFromDate, expectedToDate)).thenReturn(Collections.singletonList(expectedBank2Transaction));
        when(transactionCheckerFactory.getTransactionChecker(BANK_1)).thenReturn(bank1TransactionCheckerStrategy);
        when(transactionCheckerFactory.getTransactionChecker(BANK_2)).thenReturn(bank2TransactionCheckerStrategy);

        // Act
        List<AccountTransaction> actualTransactions = transactionService
                .getTransactions(expectedInternalAccountId, expectedFromDate, expectedToDate);

        // Assert
        verify(transactionCheckerFactory, times(2))
                .getTransactionChecker(any(BankInstitution.class));
        verify(accountService, times(1)).getAssociatedBankAccounts(expectedInternalAccountId);
        assertIterableEquals(expectedTransactions, actualTransactions);
    }

    @Test(expected = BankInstitutionNotSupportedException.class)
    public void getTransactions_whenBankInstitutionNotSupportedIsThrown_shouldRethrowTheSameException()
            throws BankInstitutionNotSupportedException, ParseException {
        // Arrange
        long expectedInternalAccountId = 123;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedFromDate = formatter.parse("2021-01-01");
        Date expectedToDate = formatter.parse("2022-01-01");
        Account expectedBank1Account = new Account(expectedInternalAccountId, 123456, BANK_1);
        Account expectedBank2Account = new Account(expectedInternalAccountId, 654321, BANK_2);
        AccountTransaction expectedBank1Transaction =
                new AccountTransaction(100.0, AccountTransactionType.CREDIT, "Check deposit");
        AccountTransaction expectedBank2Transaction =
                new AccountTransaction(25.5, AccountTransactionType.DEBIT, "Debit card purchase");
        List<Account> expectedAccounts = Arrays.asList(expectedBank1Account, expectedBank2Account);

        when(accountService.getAssociatedBankAccounts(expectedInternalAccountId)).thenReturn(expectedAccounts);
        when(bank1TransactionCheckerStrategy.getTransactions(expectedBank1Account.getExternalId(),
                expectedFromDate, expectedToDate)).thenReturn(Collections.singletonList(expectedBank1Transaction));
        when(transactionCheckerFactory.getTransactionChecker(BANK_1)).thenReturn(bank1TransactionCheckerStrategy);
        when(transactionCheckerFactory.getTransactionChecker(BANK_2))
                .thenThrow(new BankInstitutionNotSupportedException("Test"));


        // Act
        transactionService.getTransactions(expectedInternalAccountId, expectedFromDate, expectedToDate);
    }
}
