package com.pyyne.bankmanager.service.balance;

import com.pyyne.bankmanager.model.bank.account.Account;
import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;
import com.pyyne.bankmanager.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final AccountService accountService;

    @Autowired
    public BalanceServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<AccountBalance> getAvailableBalances(long internalAccountId) {
        List<Account> accounts = accountService.getAssociatedBankAccounts(internalAccountId);

        return null;
    }
}
