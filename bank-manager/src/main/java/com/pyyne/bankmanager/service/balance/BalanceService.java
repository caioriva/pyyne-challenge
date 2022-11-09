package com.pyyne.bankmanager.service.balance;

import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;

import java.util.List;

public interface BalanceService {

    List<AccountBalance> getAvailableBalances(long internalAccountId);
}
