package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        BankAccount bankAccount = new BankAccount(1000);

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
        BankAccount bankAccountSender = new BankAccount(1000);
        BankAccount bankAccountReceiver = new BankAccount(0);

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
    void shouldApplyTransferenceFeeWhenTransferringToOtherBankAccount() throws Exception {
        // Arrange (Given)
        int transferenceFee = 10;
        BankAccount bankAccountSender = new BankAccount(1000, transferenceFee);
        BankAccount bankAccountReceiver = new BankAccount(0, transferenceFee);

        // Act (When)
        bankAccountSender.transfer(500, bankAccountReceiver);

        // Assert (Then)
        assertThat(bankAccountSender.getBalance(), is(equalTo(490)));
        assertThat(bankAccountReceiver.getBalance(), is(equalTo(500)));
    }
}
