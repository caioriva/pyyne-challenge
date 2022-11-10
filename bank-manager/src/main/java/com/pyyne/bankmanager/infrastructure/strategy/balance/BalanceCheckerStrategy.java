package com.pyyne.bankmanager.infrastructure.strategy.balance;

import com.pyyne.bankmanager.model.balance.AccountBalance;

public interface BalanceCheckerStrategy {

    AccountBalance getBalance(long externalAccountId);
}
