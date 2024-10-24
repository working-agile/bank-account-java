package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankStatementStubTest {

    @DisplayName("Overdraft should trigger an email")
    @Test
    void bankstatement_with_empty_transaction_history () {

        // Arrange (Given)
        EmailSender emailSenderMock = mock(EmailSender.class);
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistoryMock);

        // Act (When)
        String bankStatement = bankAccount.getBankStatement();

        // Assert (Then)
        assertThat(bankStatement, is(equalTo("balance:1000,transactionHistory:empty")));
    }






}
