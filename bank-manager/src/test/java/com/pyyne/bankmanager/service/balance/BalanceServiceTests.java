package com.pyyne.bankmanager.service.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.factory.balance.BalanceCheckerFactory;
import com.pyyne.bankmanager.infrastructure.strategy.balance.BalanceCheckerStrategy;
import com.pyyne.bankmanager.infrastructure.strategy.balance.Bank1BalanceChecker;
import com.pyyne.bankmanager.infrastructure.strategy.balance.Bank2BalanceChecker;
import com.pyyne.bankmanager.model.AccountCurrency;
import com.pyyne.bankmanager.model.BankInstitution;
import com.pyyne.bankmanager.model.account.Account;
import com.pyyne.bankmanager.model.balance.AccountBalance;
import com.pyyne.bankmanager.service.account.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.pyyne.bankmanager.model.BankInstitution.BANK_1;
import static com.pyyne.bankmanager.model.BankInstitution.BANK_2;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class BalanceServiceTests {
    private AccountService accountService;
    private BalanceCheckerFactory balanceCheckerFactory;
    private BalanceCheckerStrategy bank1BalanceCheckerStrategy;
    private BalanceCheckerStrategy bank2BalanceCheckerStrategy;
    private BalanceService balanceService;

    @Before
    public void setUp() {
        accountService = mock(AccountService.class);
        balanceCheckerFactory = mock(BalanceCheckerFactory.class);
        bank1BalanceCheckerStrategy = mock(Bank1BalanceChecker.class);
        bank2BalanceCheckerStrategy = mock(Bank2BalanceChecker.class);
        balanceService = new BalanceServiceImpl(accountService, balanceCheckerFactory);
    }

    @Test
    public void getAvailableBalances_inTheNormalWorkflow_shouldReturnCorrectBalanceList()
            throws BankInstitutionNotSupportedException {
        // Arrange
        long expectedInternalAccountId = 123;
        Account expectedBank1Account = new Account(expectedInternalAccountId, 123456, BANK_1);
        Account expectedBank2Account = new Account(expectedInternalAccountId, 654321, BANK_2);
        AccountBalance expectedBank1Balance = new AccountBalance(215.5, AccountCurrency.USD);
        AccountBalance expectedBank2Balance = new AccountBalance(512.5, AccountCurrency.USD);
        List<Account> expectedAccounts = Arrays.asList(expectedBank1Account, expectedBank2Account);
        List<AccountBalance> expectedBalances = Arrays.asList(expectedBank1Balance, expectedBank2Balance);

        when(accountService.getAssociatedBankAccounts(expectedInternalAccountId)).thenReturn(expectedAccounts);
        when(bank1BalanceCheckerStrategy.getBalance(expectedBank1Account.getExternalId()))
                .thenReturn(expectedBank1Balance);
        when(bank2BalanceCheckerStrategy.getBalance(expectedBank2Account.getExternalId()))
                .thenReturn(expectedBank2Balance);
        when(balanceCheckerFactory.getBalanceChecker(BANK_1)).thenReturn(bank1BalanceCheckerStrategy);
        when(balanceCheckerFactory.getBalanceChecker(BANK_2)).thenReturn(bank2BalanceCheckerStrategy);

        // Act
        List<AccountBalance> actualBalances = balanceService.getAvailableBalances(expectedInternalAccountId);

        // Assert
        verify(balanceCheckerFactory, times(2)).getBalanceChecker(any(BankInstitution.class));
        verify(accountService, times(1)).getAssociatedBankAccounts(expectedInternalAccountId);
        assertIterableEquals(expectedBalances, actualBalances);
    }

    @Test(expected = BankInstitutionNotSupportedException.class)
    public void getAvailableBalances_whenBankInstitutionNotSupportedIsThrown_shouldRethrowTheSameException()
            throws BankInstitutionNotSupportedException {
        // Arrange
        long expectedInternalAccountId = 123;
        Account expectedBank1Account = new Account(expectedInternalAccountId, 123456, BANK_1);
        Account expectedBank2Account = new Account(expectedInternalAccountId, 654321, BANK_2);
        AccountBalance expectedBank1Balance = new AccountBalance(215.5, AccountCurrency.USD);
        List<Account> expectedAccounts = Arrays.asList(expectedBank1Account, expectedBank2Account);

        when(accountService.getAssociatedBankAccounts(expectedInternalAccountId)).thenReturn(expectedAccounts);
        when(bank1BalanceCheckerStrategy.getBalance(expectedBank1Account.getExternalId()))
                .thenReturn(expectedBank1Balance);
        when(balanceCheckerFactory.getBalanceChecker(BANK_1)).thenReturn(bank1BalanceCheckerStrategy);
        when(balanceCheckerFactory.getBalanceChecker(BANK_2))
                .thenThrow(new BankInstitutionNotSupportedException("Test"));

        // Act
        balanceService.getAvailableBalances(expectedInternalAccountId);
    }
}
