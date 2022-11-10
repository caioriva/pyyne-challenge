package com.pyyne.bankmanager.service.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.model.balance.AccountBalance;

import java.util.List;

public interface BalanceService {

    List<AccountBalance> getAvailableBalances(long internalAccountId) throws BankInstitutionNotSupportedException;
}
