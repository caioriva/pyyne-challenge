package com.pyyne.bankmanager.infrastructure.factory.transaction;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.Bank1TransactionChecker;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.Bank2TransactionChecker;
import com.pyyne.bankmanager.infrastructure.strategy.transaction.TransactionCheckerStrategy;
import com.pyyne.bankmanager.model.BankInstitution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.pyyne.bankmanager.model.BankInstitution.BANK_1;
import static com.pyyne.bankmanager.model.BankInstitution.BANK_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionCheckerFactoryTests {
    private Bank1TransactionChecker bank1TransactionChecker;
    private Bank2TransactionChecker bank2TransactionChecker;
    private TransactionCheckerFactory balanceCheckerFactory;

    @BeforeEach
    public void setUp() {
        bank1TransactionChecker = new Bank1TransactionChecker();
        bank2TransactionChecker = new Bank2TransactionChecker();
        balanceCheckerFactory = new TransactionCheckerFactoryImpl(bank1TransactionChecker, bank2TransactionChecker);
    }

    @ParameterizedTest
    @MethodSource("provideSourceAndAssertDataForNormalWorkflow")
    public void getBalanceChecker_inTheNormalWorkflow_shouldReturnCorrectBalanceChecker(
            BankInstitution bankInstitution, Class<TransactionCheckerStrategy> classType)
            throws BankInstitutionNotSupportedException {
        // Act
        TransactionCheckerStrategy strategy = balanceCheckerFactory.getTransactionChecker(bankInstitution);

        // Assert
        assertEquals(classType, strategy.getClass());
    }

    private static Stream<Arguments> provideSourceAndAssertDataForNormalWorkflow() {
        return Stream.of(
                Arguments.of(BANK_1, Bank1TransactionChecker.class),
                Arguments.of(BANK_2, Bank2TransactionChecker.class)
        );
    }
}
