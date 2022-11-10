package com.pyyne.bankmanager.infrastructure.strategy.transaction;

import com.pyyne.bankmanager.model.transaction.AccountTransaction;

import java.util.Date;
import java.util.List;

public interface TransactionCheckerStrategy {

    List<AccountTransaction> getTransactions(long externalAccountId, Date fromDate, Date toDate);
}
