package com.pyyne.bankmanager.infrastructure.strategy.balance;

import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountBalance;
import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountSource;
import com.pyyne.bankmanager.model.AccountCurrency;
import com.pyyne.bankmanager.model.balance.AccountBalance;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bank2BalanceCheckerTests {

    @Test
    public void getBalance_inTheNormalWorkflow_shouldReturnCorrectHardcodedAccountBalance() {
        // Arrange
        long expectedExternalAccountId = 123;
        Bank2AccountSource accountSource = new Bank2AccountSource();
        Bank2AccountBalance accountBalance = accountSource.getBalance(expectedExternalAccountId);
        AccountBalance expectedAccountBalance = new AccountBalance(accountBalance.getBalance(),
                AccountCurrency.valueOf(accountBalance.getCurrency()));

        // Act
        AccountBalance actualAccountBalance = new Bank2BalanceChecker().getBalance(expectedExternalAccountId);

        // Assert
        assertEquals(expectedAccountBalance, actualAccountBalance);
    }
}
