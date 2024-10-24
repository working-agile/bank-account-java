package com.workingagile.acsd;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryInMemory implements TransactionHistory{

    private final List<Integer> transactions;

    public TransactionHistoryInMemory() {
        transactions = new ArrayList<>();
    }

    @Override
    public void informTransaction(String transaction, int amount) {
        if (transaction.equals("withdraw")) {
            amount = -amount;
        }
        transactions.add(amount);
    }

    @Override
    public List<Integer> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }




}
