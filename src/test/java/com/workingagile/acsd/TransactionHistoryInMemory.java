package com.workingagile.acsd;

import java.util.List;

public class TransactionHistoryInMemory implements TransactionHistory{

    @Override
    public void informTransaction(String transaction, int amount) {
    }

    @Override
    public List<Integer> getTransactionHistory() {
        return null;
    }
}
