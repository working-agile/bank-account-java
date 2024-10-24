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

    @DisplayName("Withdrawals should be informed to the transaction history")
    @Test
    void the_withdrawal_is_informed_to_the_transaction_history() throws Exception {
        // Arrange (Given)
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        EmailSender fakeEmailSender = mock(EmailSender.class);
        BankAccount bankAccount = new BankAccount(1000, 0, fakeEmailSender, transactionHistoryMock);

        // Act (When)
        bankAccount.withdraw(200);

        // Assert (Then)
        verify(transactionHistoryMock, times(1)).informTransaction("withdraw", 200);
    }

    @DisplayName("Withdrawal that goes not through is not informed to the transaction history")
    @Test
    void aborted_withdrawals_are_not_informed_to_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        EmailSender fakeEmailSender = mock(EmailSender.class);
        BankAccount bankAccount = new BankAccount(1000, 0, fakeEmailSender, transactionHistoryMock);

        // Act (When)
        try {
            bankAccount.withdraw(1001);
        } catch (Exception e) {}

        // Assert (Then)
        verify(transactionHistoryMock, never()).informTransaction(any(String.class), (anyInt()));
    }


    @DisplayName("Successful transfers are registered in the TransactionHistory")
    @Test
    void successful_transfers_are_registered_in_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistorySenderMock = mock(TransactionHistory.class);
        TransactionHistory transactionHistoryReceiverMock = mock(TransactionHistory.class);
        EmailSender fakeEmailSender = mock(EmailSender.class);
        BankAccount bankAccountSender = new BankAccount(1000, 0, fakeEmailSender, transactionHistorySenderMock);
        BankAccount bankAccountReceiver = new BankAccount(1000, 0, fakeEmailSender, transactionHistoryReceiverMock);

        // Act (When)
        try {
            bankAccountSender.transfer(300, bankAccountReceiver);
        } catch (Exception e) {}

        // Assert (Then)
        verify(transactionHistorySenderMock, times(1)).informTransaction("withdraw", 300);
        verify(transactionHistoryReceiverMock, times(1)).informTransaction("deposit", 300);
    }

    @DisplayName("Unsuccessful transfers are not registered in the TransactionHistory")
    @Test
    void unsuccessful_transfers_are_not_registered_in_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistorySenderMock = mock(TransactionHistory.class);
        TransactionHistory transactionHistoryReceiverMock = mock(TransactionHistory.class);
        EmailSender fakeEmailSender = mock(EmailSender.class);
        BankAccount bankAccountSender = new BankAccount(1000, 0, fakeEmailSender, transactionHistorySenderMock);
        BankAccount bankAccountReceiver = new BankAccount(1000, 0, fakeEmailSender, transactionHistoryReceiverMock);

        // Act (When)
        try {
            bankAccountSender.transfer(1001, bankAccountReceiver);
        } catch (Exception e) {}

        // Assert (Then)
        verify(transactionHistorySenderMock, never()).informTransaction(any(String.class), (anyInt()));
        verify(transactionHistoryReceiverMock, never()).informTransaction(any(String.class), (anyInt()));
    }



}
