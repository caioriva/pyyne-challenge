package com.pyyne.bankmanager.service.account;

import com.pyyne.bankmanager.model.bank.account.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAssociatedBankAccounts(long internalAccountId);
}
