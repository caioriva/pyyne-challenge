package com.pyyne.bankmanager.infrastructure.strategy.balance;

import com.pyyne.bankmanager.infrastructure.bank1.integration.Bank1AccountSource;
import com.pyyne.bankmanager.model.AccountCurrency;
import com.pyyne.bankmanager.model.balance.AccountBalance;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bank1BalanceCheckerTests {

    @Test
    public void getBalance_inTheNormalWorkflow_shouldReturnCorrectHardcodedAccountBalance() {
        // Arrange
        long expectedExternalAccountId = 123;
        Bank1AccountSource accountSource = new Bank1AccountSource();
        AccountBalance expectedAccountBalance =
                new AccountBalance(accountSource.getAccountBalance(expectedExternalAccountId),
                AccountCurrency.valueOf(accountSource.getAccountCurrency(expectedExternalAccountId)));

        // Act
        AccountBalance actualAccountBalance = new Bank1BalanceChecker().getBalance(expectedExternalAccountId);

        // Assert
        assertEquals(expectedAccountBalance, actualAccountBalance);
    }
}
