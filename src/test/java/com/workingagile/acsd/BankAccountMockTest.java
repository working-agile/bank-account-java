package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountMockTest {

    @DisplayName("Overdraft should trigger an email")
    @Test
    void should_trigger_email_when_overdrafting () {
        
        // Arrange (Given)
        FakeEmailSender fakeEmailSender = new FakeEmailSender();
        BankAccount bankAccount = new BankAccount(1000, 0, fakeEmailSender);

        // Act (When)
        try {
            bankAccount.withdraw(1100);
            fail("withdrawal not expected to go through");
        } catch (BankAccount.InsufficientBalanceException e) {}

        // Assert (Then)
        assertThat(fakeEmailSender.hasSentEmail, is(true));
        assertThat(fakeEmailSender.howManyTimesSentEmail, is(1));

    }
}
