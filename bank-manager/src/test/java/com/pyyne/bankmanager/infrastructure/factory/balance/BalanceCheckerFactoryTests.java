package com.pyyne.bankmanager.infrastructure.factory.balance;

import com.pyyne.bankmanager.exceptions.BankInstitutionNotSupportedException;
import com.pyyne.bankmanager.infrastructure.strategy.balance.BalanceCheckerStrategy;
import com.pyyne.bankmanager.infrastructure.strategy.balance.Bank1BalanceChecker;
import com.pyyne.bankmanager.infrastructure.strategy.balance.Bank2BalanceChecker;
import com.pyyne.bankmanager.model.BankInstitution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.pyyne.bankmanager.model.BankInstitution.BANK_1;
import static com.pyyne.bankmanager.model.BankInstitution.BANK_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceCheckerFactoryTests {

    private Bank1BalanceChecker bank1BalanceChecker;
    private Bank2BalanceChecker bank2BalanceChecker;
    private BalanceCheckerFactory balanceCheckerFactory;

    @BeforeEach
    public void setUp() {
        bank1BalanceChecker = new Bank1BalanceChecker();
        bank2BalanceChecker = new Bank2BalanceChecker();
        balanceCheckerFactory = new BalanceCheckerFactoryImpl(bank1BalanceChecker, bank2BalanceChecker);
    }

    @ParameterizedTest
    @MethodSource("provideSourceAndAssertDataForNormalWorkflow")
    public void getBalanceChecker_inTheNormalWorkflow_shouldReturnCorrectBalanceChecker(
            BankInstitution bankInstitution, Class<BalanceCheckerStrategy> classType)
            throws BankInstitutionNotSupportedException {
        // Act
        BalanceCheckerStrategy strategy = balanceCheckerFactory.getBalanceChecker(bankInstitution);

        // Assert
        assertEquals(classType, strategy.getClass());
    }

    private static Stream<Arguments> provideSourceAndAssertDataForNormalWorkflow() {
        return Stream.of(
                Arguments.of(BANK_1, Bank1BalanceChecker.class),
                Arguments.of(BANK_2, Bank2BalanceChecker.class)
        );
    }
}
