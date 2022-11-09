package com.pyyne.bankmanager.service.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupported;
import com.pyyne.bankmanager.model.bank.account.transaction.AccountTransaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<AccountTransaction> getTransactions(long internalAccountId, Date fromDate, Date toDate) throws BankInstitutionNotSupported;
}
