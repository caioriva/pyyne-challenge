package com.pyyne.bankmanager.service.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.infrastructure.factory.balance.BalanceCheckerFactory;
import com.pyyne.bankmanager.model.account.Account;
import com.pyyne.bankmanager.model.balance.AccountBalance;
import com.pyyne.bankmanager.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final AccountService accountService;
    private final BalanceCheckerFactory balanceCheckerFactory;

    @Autowired
    public BalanceServiceImpl(AccountService accountService, BalanceCheckerFactory balanceCheckerFactory) {
        this.accountService = accountService;
        this.balanceCheckerFactory = balanceCheckerFactory;
    }

    @Override
    public List<AccountBalance> getAvailableBalances(long internalAccountId) throws BankInstitutionNotSupported {
        List<Account> accounts = accountService.getAssociatedBankAccounts(internalAccountId);
        List<AccountBalance> balances = new ArrayList<>();

        for (Account account: accounts) {
            balances.add(balanceCheckerFactory
                    .getBalanceChecker(account.getAssociatedBank())
                    .getBalance(account.getExternalId()));
        }

        return balances;
    }
}
