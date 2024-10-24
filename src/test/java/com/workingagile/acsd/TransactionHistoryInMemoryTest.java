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

    @DisplayName("informing a deposit and a withdrawal")
    @Test
    void informing_a_deposit_and_a_withdrawal() {

        // Arrange (Given)
        TransactionHistoryInMemory transactionHistory = new TransactionHistoryInMemory();

        // Act (When)
        transactionHistory.informTransaction("deposit", 100);
        transactionHistory.informTransaction("withdraw", 50);

        // Assert (Then)
        List<Integer> transactionList = transactionHistory.getTransactionHistory();
        assertThat(transactionList, is(not(empty())));
        assertThat(transactionList.size(), is(2));
        assertThat(transactionList.get(0), is(100));
        assertThat(transactionList.get(1), is(-50));

    }

    @DisplayName("the transaction history does not expose internal objects")
    @Test
    void the_transaction_history_does_not_expose_internal_objects() {

        // Arrange (Given)
        TransactionHistoryInMemory transactionHistory = new TransactionHistoryInMemory();
        transactionHistory.informTransaction("deposit", 100);
        List<Integer> transactionList = transactionHistory.getTransactionHistory();

        // Act (When)
        transactionList.set(0, 200);

        // Assert (Then)
        List<Integer> transactionList2 = transactionHistory.getTransactionHistory();
        assertThat(transactionList2.get(0), is(100));

    }


}
