package com.workingagile.acsd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

public class BankAccountTransferUnitTest {

    EmailSender emailSenderDummy;
    TransactionHistory transactionHistoryDummy;

    @BeforeEach
    void setupMocks() {
        emailSenderDummy = mock(EmailSender.class);
        transactionHistoryDummy = mock(TransactionHistory.class);
    }

    @DisplayName("Should transfer a value to other account")
    @Test
    void shouldTransferMoneyToOtherBankAccount() throws Exception {
        // Arrange (Given)
        BankAccount bankAccountSender = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryDummy);
        BankAccount bankAccountReceiver = new BankAccount(0, 0, emailSenderDummy, transactionHistoryDummy);

        // Act (When)
        bankAccountSender.transfer(500, bankAccountReceiver);

        // Assert (Then)
        assertThat(bankAccountSender.getBalance(), is(equalTo(500)));
        assertThat(bankAccountReceiver.getBalance(), is(equalTo(500)));
    }

    @DisplayName("Transfer amount higher than the balance, should not transfer")
    @Test
    void shouldNotTransferWhenTransferAmountIsHigherThanTheBalance() {
        // Arrange (Given)
        BankAccount bankAccountSender = new BankAccount(1000, 0, emailSenderDummy, transactionHistoryDummy);
        BankAccount bankAccountReceiver = new BankAccount(0, 0, emailSenderDummy, transactionHistoryDummy);

        // Act (When)
        try {
            bankAccountSender.transfer(1100, bankAccountReceiver);
            fail("transfer not expected to go through");
        } catch (BankAccount.InsufficientBalanceException e) {}

        // Assert (Then)
        assertThat(bankAccountSender.getBalance(), is(equalTo(1000)));
        assertThat(bankAccountReceiver.getBalance(), is(equalTo(0)));
    }

    @DisplayName("Transfer fee is charged when transferring money")
    @Test
    void shouldApplyTransferFeeWhenTransferringToOtherBankAccount() throws Exception {

        // Arrange (Given)
        int transferFee = 10;
        BankAccount bankAccountSender = new BankAccount(1000, transferFee, emailSenderDummy, transactionHistoryDummy);
        BankAccount bankAccountReceiver = new BankAccount(0, transferFee, emailSenderDummy, transactionHistoryDummy);

        // Act (When)
        bankAccountSender.transfer(500, bankAccountReceiver);

        // Assert (Then)
        assertThat(bankAccountSender.getBalance(), is(equalTo(490)));
        assertThat(bankAccountReceiver.getBalance(), is(equalTo(500)));
    }

}
