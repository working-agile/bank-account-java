package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

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



}
