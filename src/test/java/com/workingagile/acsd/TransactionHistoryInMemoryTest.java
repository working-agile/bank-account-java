package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class TransactionHistoryInMemoryTest {

    @DisplayName("returns the transaction history without transactions")
    @Test
    void empty_transaction_history() {

        // Arrange (Given)
        TransactionHistoryInMemory transactionHistory = new TransactionHistoryInMemory();

        // Act (When)
        List<Integer> transactionList = transactionHistory.getTransactionHistory();

        // Assert (Then)
        assertThat(transactionList, is(empty()));
    }

    @DisplayName("informing a single deposit")
    @Test
    void informing_a_single_deposit() {

        // Arrange (Given)
        TransactionHistoryInMemory transactionHistory = new TransactionHistoryInMemory();

        // Act (When)
        transactionHistory.informTransaction("deposit", 50);

        // Assert (Then)
        List<Integer> transactionList = transactionHistory.getTransactionHistory();
        assertThat(transactionList, is(not(empty())));
        assertThat(transactionList.size(), is(1));
        assertThat(transactionList.get(0), is(50));

    }

    @DisplayName("informing a single withdrawal")
    @Test
    void informing_a_single_withdrawal() {

        // Arrange (Given)
        TransactionHistoryInMemory transactionHistory = new TransactionHistoryInMemory();

        // Act (When)
        transactionHistory.informTransaction("withdraw", 50);

        // Assert (Then)
        List<Integer> transactionList = transactionHistory.getTransactionHistory();
        assertThat(transactionList, is(not(empty())));
        assertThat(transactionList.size(), is(1));
        assertThat(transactionList.get(0), is(-50));

    }


}
