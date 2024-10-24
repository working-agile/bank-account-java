package com.workingagile.acsd;

import java.util.List;

public interface TransactionHistory {

    void informTransaction(String transaction, int amount);

    List<Integer> getTransactionHistory();
}
