package com.pyyne.bankmanager.service.transaction;

import com.pyyne.bankmanager.model.bank.account.balance.AccountBalance;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<AccountBalance> getAvailableTransactions(long internalAccountId, Date fromDate, Date toDate);
}
