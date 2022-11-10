package com.pyyne.bankmanager.service.account;

import com.pyyne.bankmanager.model.account.Account;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.pyyne.bankmanager.model.BankInstitution.BANK_1;
import static com.pyyne.bankmanager.model.BankInstitution.BANK_2;

/**
 * Returns dummy data for the sake of testing. Ideally this service would call a repository class and retrieve the
 * bank accounts associated to that internal ID from the database.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public List<Account> getAssociatedBankAccounts(long internalAccountId) {
        return Arrays.asList(new Account(internalAccountId, 123456, BANK_1),
                new Account(internalAccountId, 654321, BANK_2));
    }
}
