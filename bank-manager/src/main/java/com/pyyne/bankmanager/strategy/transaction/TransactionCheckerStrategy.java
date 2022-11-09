package com.pyyne.bankmanager.strategy.transaction;

import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransaction;

import java.util.Date;
import java.util.List;

public interface TransactionCheckerStrategy {

    List<AccountTransaction> getTransactions(long externalAccountId, Date fromDate, Date toDate);
}
