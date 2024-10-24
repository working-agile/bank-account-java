package com.workingagile.acsd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class BankStatementStubTest {

    EmailSender emailSenderMock;

    @BeforeEach
    void setupMocks() {
        emailSenderMock = mock(EmailSender.class);
    }


    @DisplayName("How a bank statement with empty transaction history looks like")
    @Test
    void bank_statement_with_empty_transaction_history () {

        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        List<Integer> emptyTransactionList = new ArrayList<>();
        when(transactionHistoryStub.getTransactionHistory()).thenReturn(emptyTransactionList);

        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistoryStub);

        // Act (When)
        String bankStatement = bankAccount.getBankStatement();

        // Assert (Then)
        assertThat(bankStatement, is(equalTo("balance:1000,transactionHistory:empty")));
    }


    @DisplayName("How a bank statement with one deposit looks like")
    @Test
    void bank_statement_with_one_deposit () {

        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        List<Integer> transactionList = new ArrayList<>();
        transactionList.add(50);
        when(transactionHistoryStub.getTransactionHistory()).thenReturn(transactionList);

        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistoryStub);

        // Act (When)
        String bankStatement = bankAccount.getBankStatement();

        // Assert (Then)
        assertThat(bankStatement, is(equalTo("balance:1000,transactionHistory:[deposit:50]")));
    }


    @DisplayName("How a bank statement with one deposit and a withdrawal looks like")
    @Test
    void bank_statement_with_one_deposit_and_a_withdrawal () {

        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        List<Integer> transactionList = new ArrayList<>();
        transactionList.add(50);
        transactionList.add(-30);
        when(transactionHistoryStub.getTransactionHistory()).thenReturn(transactionList);

        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistoryStub);

        // Act (When)
        String bankStatement = bankAccount.getBankStatement();

        // Assert (Then)
        assertThat(bankStatement, is(equalTo("balance:1000,transactionHistory:[deposit:50,withdrawal:30]")));
    }

    @DisplayName("How a complete bank statement should look like ")
    @Test
    void complete_bank_statement_looks_like () {

        // Arrange (Given)
        TransactionHistory transactionHistoryStub = mock(TransactionHistory.class);
        List<Integer> transactionList = new ArrayList<>();
        transactionList.add(50);
        transactionList.add(-30);
        transactionList.add(-10);
        transactionList.add(550);
        when(transactionHistoryStub.getTransactionHistory()).thenReturn(transactionList);

        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistoryStub);

        // Act (When)
        String bankStatement = bankAccount.getBankStatement();

        // Assert (Then)
        assertThat(bankStatement, is(equalTo(
                "balance:1000,transactionHistory:[deposit:50,withdrawal:30,withdrawal:10,deposit:550]")));
    }

}
