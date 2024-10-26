package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankAccountMockTest {

    @DisplayName("Overdraft should trigger an email")
    @Test
    void should_trigger_email_when_overdrafting () {
        
        // Arrange (Given)
        EmailSender emailSenderMock = mock(EmailSender.class);
        TransactionHistory transactionHistoryDummy = mock(TransactionHistory.class);
        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistoryDummy);

        // Act (When)
        try {
            bankAccount.withdraw(1100);
            fail("withdrawal not expected to go through");
        } catch (BankAccount.InsufficientBalanceException e) {}

        // Assert (Then)
        verify(emailSenderMock, times(1)).sendEmailToBank();
    }

}
