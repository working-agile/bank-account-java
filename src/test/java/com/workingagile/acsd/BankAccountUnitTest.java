package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class BankAccountUnitTest {

    @DisplayName("Deposits should increase the balance according to the amount")
    @Test
    void depositAmount() {
        // Arrange (Given)
        BankAccount bankAccount = new BankAccount(1000);

        // Act (When)
        bankAccount.deposit(200);

        // Assert (Then)
        assertThat(bankAccount.getBalance(), is(equalTo(1200)));
    }

    @DisplayName("Withdrawals should decrease the balance according to the amount")
    @Test
    void withdrawAmount() throws Exception {
        // Arrange (Given)
        BankAccount bankAccount = new BankAccount(1000);

        // Act (When)
        bankAccount.withdraw(200);

        // Assert (Then)
        assertThat(bankAccount.getBalance(), is(equalTo(800)));
    }

    @DisplayName("Overdrawing the bank account is not allowed")
    @Test
    void overdrawingAmount() {
        // Arrange (Given)
        EmailSender dummyEmailSender = mock(EmailSender.class);
        BankAccount bankAccount = new BankAccount(1000, 100, dummyEmailSender);

        // Act (When)
        try {
            bankAccount.withdraw(1100);
            fail("withdrawal not expected to go through");
        } catch (BankAccount.InsufficientBalanceException e) {}

        // Assert (Then)
        assertThat(bankAccount.getBalance(), is(equalTo(1000)));
    }

    @DisplayName("Should transfer a value to other account")
    @Test
    void shouldTransferMoneyToOtherBankAccount() throws Exception {
        // Arrange (Given)
        BankAccount bankAccountSender = new BankAccount(1000);
        BankAccount bankAccountReceiver = new BankAccount(0);

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
        EmailSender dummyEmailSender = mock(EmailSender.class);
        BankAccount bankAccountSender = new BankAccount(1000, 0, dummyEmailSender);
        BankAccount bankAccountReceiver = new BankAccount(0, 0, dummyEmailSender);

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
        EmailSender dummyEmailSender = mock(EmailSender.class);
        int transferFee = 10;
        BankAccount bankAccountSender = new BankAccount(1000, transferFee, dummyEmailSender);
        BankAccount bankAccountReceiver = new BankAccount(0, transferFee, dummyEmailSender);

        // Act (When)
        bankAccountSender.transfer(500, bankAccountReceiver);

        // Assert (Then)
        assertThat(bankAccountSender.getBalance(), is(equalTo(490)));
        assertThat(bankAccountReceiver.getBalance(), is(equalTo(500)));
    }

}
