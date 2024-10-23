package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TransactionHistoryTest {

    @DisplayName("Deposits should be informed to the transaction history")
    @Test
    void the_deposit_is_informed_to_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        EmailSender fakeEmailSender = mock(EmailSender.class);
        BankAccount bankAccount = new BankAccount(1000, 0, fakeEmailSender, transactionHistoryMock);

        // Act (When)
        bankAccount.deposit(200);

        // Assert (Then)
        verify(transactionHistoryMock, times(1)).informTransaction("deposit", 200);
    }

}
