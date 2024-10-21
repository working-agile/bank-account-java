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
}
