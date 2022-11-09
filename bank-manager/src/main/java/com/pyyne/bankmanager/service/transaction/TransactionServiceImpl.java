package com.pyyne.bankmanager.service.transaction;

import com.pyyne.bankmanager.model.bank.account.Account;
import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;
import com.pyyne.bankmanager.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;

    @Autowired
    public TransactionServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<AccountBalance> getAvailableTransactions(long internalAccountId, Date fromDate, Date toDate) {
        List<Account> accounts = accountService.getAssociatedBankAccounts(internalAccountId);

        return null;
    }
}
