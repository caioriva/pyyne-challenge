package com.pyyne.bankmanager.strategy;

import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;

public interface BalanceCheckerStrategy {

    AccountBalance getBalance(long externalAccountId);
}
