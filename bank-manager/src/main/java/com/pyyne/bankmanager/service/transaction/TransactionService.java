package com.pyyne.bankmanager.service.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<AccountTransaction> getTransactions(long internalAccountId, Date fromDate, Date toDate) throws BankInstitutionNotSupportedException;
}
