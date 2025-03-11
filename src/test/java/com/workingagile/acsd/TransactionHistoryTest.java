package com.workingagile.acsd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TransactionHistoryTest {

    EmailSender emailSenderDummy;

    @BeforeEach
    void setupMocks() {
        emailSenderDummy = mock(EmailSender.class);
    }

    @DisplayName("Deposits should be informed to the transaction history")
    @Test
    void the_deposit_is_informed_to_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryStub);

        // Act (When)
        bankAccount.deposit(200);

        // Assert (Then)
        verify(transactionHistoryStub, times(1)).informTransaction("deposit", 200);
    }

    @DisplayName("Withdrawals should be informed to the transaction history")
    @Test
    void the_withdrawal_is_informed_to_the_transaction_history() throws Exception {
        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryStub);

        // Act (When)
        bankAccount.withdraw(200);

        // Assert (Then)
        verify(transactionHistoryStub, times(1)).informTransaction("withdraw", 200);
    }

    @DisplayName("Withdrawal that goes not through is not informed to the transaction history")
    @Test
    void aborted_withdrawals_are_not_informed_to_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryStub);

        // Act (When)
        try {
            bankAccount.withdraw(1001);
        } catch (Exception e) {}

        // Assert (Then)
        verify(transactionHistoryStub, never()).informTransaction(any(String.class), (anyInt()));
    }


    @DisplayName("Successful transfers are registered in the TransactionHistory")
    @Test
    void successful_transfers_are_registered_in_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistorySenderStub = mock(TransactionHistory.class);
        TransactionHistory transactionHistoryReceiverStub = mock(TransactionHistory.class);
        BankAccount bankAccountSender = new BankAccount(1000, 0, emailSenderDummy, transactionHistorySenderStub);
        BankAccount bankAccountReceiver = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryReceiverStub);

        // Act (When)
        try {
            bankAccountSender.transfer(300, bankAccountReceiver);
        } catch (Exception e) {}

        // Assert (Then)
        verify(transactionHistorySenderStub, times(1)).informTransaction("withdraw", 300);
        verify(transactionHistoryReceiverStub, times(1)).informTransaction("deposit", 300);
    }

    @DisplayName("Unsuccessful transfers are not registered in the TransactionHistory")
    @Test
    void unsuccessful_transfers_are_not_registered_in_the_transaction_history() {
        // Arrange (Given)
        TransactionHistory transactionHistorySenderStub = mock(TransactionHistory.class);
        TransactionHistory transactionHistoryReceiverStub = mock(TransactionHistory.class);
        BankAccount bankAccountSender = new BankAccount(1000, 0, emailSenderDummy, transactionHistorySenderStub);
        BankAccount bankAccountReceiver = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryReceiverStub);

        // Act (When)
        try {
            bankAccountSender.transfer(1001, bankAccountReceiver);
        } catch (Exception e) {}

        // Assert (Then)
        verify(transactionHistorySenderStub, never()).informTransaction(any(String.class), (anyInt()));
        verify(transactionHistoryReceiverStub, never()).informTransaction(any(String.class), (anyInt()));
    }



}
