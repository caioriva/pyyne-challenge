package com.pyyne.bankmanager.service.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.factory.transaction.TransactionCheckerFactory;
import com.pyyne.bankmanager.model.account.Account;
import com.pyyne.bankmanager.model.transaction.AccountTransaction;
import com.pyyne.bankmanager.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;

    private final TransactionCheckerFactory transactionCheckerFactory;

    @Autowired
    public TransactionServiceImpl(AccountService accountService, TransactionCheckerFactory transactionCheckerFactory) {
        this.accountService = accountService;
        this.transactionCheckerFactory = transactionCheckerFactory;
    }

    @Override
    public List<AccountTransaction> getTransactions(long internalAccountId, Date fromDate, Date toDate)
            throws BankInstitutionNotSupportedException {
        List<Account> accounts = accountService.getAssociatedBankAccounts(internalAccountId);
        List<AccountTransaction> transactions = new ArrayList<>();

        for (Account account: accounts) {
            transactions = Stream.concat(transactions.stream(),
                    transactionCheckerFactory
                            .getTransactionChecker(account.getAssociatedBank())
                            .getTransactions(account.getExternalId(), fromDate, toDate).stream()).toList();
        }

        return transactions;
    }
}
