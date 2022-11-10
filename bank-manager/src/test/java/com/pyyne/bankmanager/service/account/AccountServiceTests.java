package com.pyyne.bankmanager.service.account;

import com.pyyne.bankmanager.model.account.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static com.pyyne.bankmanager.model.BankInstitution.BANK_1;
import static com.pyyne.bankmanager.model.BankInstitution.BANK_2;

public class AccountServiceTests {

    private AccountService accountService;

    @Before
    public void setUp() {
        accountService = new AccountServiceImpl();
    }

    @Test
    public void getAssociatedBankAccounts_inTheNormalWorkflow_shouldReturnCorrectHardcodedAccountList() {
        // Arrange
        long expectedAccountId = 123;
        List<Account> expectedAccounts = Arrays.asList(new Account(expectedAccountId, 123456, BANK_1),
                         new Account(expectedAccountId, 654321, BANK_2));

        // Act
        List<Account> actualAccounts = accountService.getAssociatedBankAccounts(expectedAccountId);

        // Assert
        Assertions.assertIterableEquals(expectedAccounts, actualAccounts);
    }
}
