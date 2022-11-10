package com.pyyne.bankmanager.controller;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.AccountCurrency;
import com.pyyne.bankmanager.model.balance.AccountBalance;
import com.pyyne.bankmanager.service.balance.BalanceService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BalanceControllerTests {

    private BalanceService balanceService;
    private BalanceController balanceController;

    @Before
    public void setUp() {
        balanceService = mock(BalanceService.class);
        balanceController = new BalanceController(balanceService);
    }

    @Test
    public void getBalances_inTheNormalWorkflow_shouldReturnCorrectBalanceList() throws BankInstitutionNotSupported {
        // Arrange
        long expectedAccountId = 123;
        List<AccountBalance> expectedBalances = Collections.singletonList(
                new AccountBalance(120d, AccountCurrency.USD));

        when(balanceService.getAvailableBalances(expectedAccountId)).thenReturn(expectedBalances);

        // Act
        List<AccountBalance> actualBalances = balanceController.getBalances(expectedAccountId);

        // Assert
        verify(balanceService, times(1)).getAvailableBalances(expectedAccountId);
        assertEquals(expectedBalances, actualBalances);
    }
}
