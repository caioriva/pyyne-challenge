package com.pyyne.bankmanager.infrastructure.strategy.balance;

import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountBalance;
import com.pyyne.bankmanager.infrastructure.bank2.integration.Bank2AccountSource;
import com.pyyne.bankmanager.model.balance.AccountBalance;
import com.pyyne.bankmanager.model.AccountCurrency;
import org.springframework.stereotype.Component;

@Component
public class Bank2BalanceChecker implements BalanceCheckerStrategy {

    private final Bank2AccountSource accountSource;

    // Instantiating the class manually for the sake of not change the challenge classes
    public Bank2BalanceChecker() {
        this.accountSource = new Bank2AccountSource();
    }

    // The conversion from a string to the enum "AccountCurrency" should be handled correctly
    // (for the case of not supported currencies) in a real life situation
    @Override
    public AccountBalance getBalance(long externalAccountId) {
        Bank2AccountBalance accountBalance = accountSource.getBalance(externalAccountId);

        return new AccountBalance(accountBalance.getBalance(), AccountCurrency.valueOf(accountBalance.getCurrency()));
    }
}
