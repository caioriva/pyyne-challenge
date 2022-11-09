package com.pyyne.bankmanager.strategy;

import com.pyyne.bankmanager.infrastructure.bank1.integration.Bank1AccountSource;
import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;
import com.pyyne.bankmanager.model.bank.account.balance.AccountCurrency;
import org.springframework.stereotype.Component;

@Component
public class Bank1BalanceChecker implements BalanceCheckerStrategy {

    private final Bank1AccountSource accountSource;

    // Instantiating the class manually for the sake of not change the challenge classes
    public Bank1BalanceChecker() {
        this.accountSource = new Bank1AccountSource();
    }

    // The conversion from a string to the enum "AccountCurrency" should be handled correctly
    // (for the case of not supported currencies) in a real life situation
    @Override
    public AccountBalance getBalance(long externalAccountId) {
        return new AccountBalance(accountSource.getAccountBalance(externalAccountId),
                AccountCurrency.valueOf(accountSource.getAccountCurrency(externalAccountId)));
    }
}
