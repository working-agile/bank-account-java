package com.workingagile.acsd;

import java.util.List;

public class BankAccount {

    private int balance;
    private final int transferFee;

    private final EmailSender emailSender;
    private final TransactionHistory transactionHistory;

    public BankAccount(int initialBalance, int transferFee, EmailSender emailSender,
                       TransactionHistory transactionHistory) {
        this.balance = initialBalance;
        this.transferFee = transferFee;
        this.emailSender = emailSender;
        this.transactionHistory = transactionHistory;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance = balance + amount;
        transactionHistory.informTransaction("deposit", amount);
    }

    public void withdraw(int amount) throws InsufficientBalanceException {

        if (amount > balance) {
            emailSender.sendEmailToBank();
            throw new InsufficientBalanceException();
        }
        balance = balance - amount;

        transactionHistory.informTransaction("withdraw", amount);
    }

    public void transfer(int amount, BankAccount receiverAccount) throws InsufficientBalanceException {
        withdraw(amount + transferFee);
        receiverAccount.deposit(amount);
    }

    public String getBankStatement() {
        List<Integer> transactionList = transactionHistory.getTransactionHistory();
        StringBuffer statement = new StringBuffer("balance:");
        statement.append(balance).append(",transactionHistory:");

        if (transactionList.isEmpty()) {
            statement.append("empty");
        } else {
            int amount = transactionList.get(0);
            statement.append("[deposit:").append(amount).append("]");
        }
        return statement.toString();
    }


    public static class InsufficientBalanceException extends Exception {}
}
