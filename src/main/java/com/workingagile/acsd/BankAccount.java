package com.workingagile.acsd;

public class BankAccount {

    private int balance;
    private final int transferenceFee;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
        this.transferenceFee = 0;
    }

    public BankAccount(int initialBalance, int transferenceFee) {
        this.balance = initialBalance;
        this.transferenceFee = transferenceFee;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance = balance + amount;
    }

    public void withdraw(int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException();
        }
        balance = balance - amount;
    }

    public void transfer(int amount, BankAccount receiverAccount) throws InsufficientBalanceException {
        withdraw(amount + transferenceFee);
        receiverAccount.deposit(amount);
    }

    public static class InsufficientBalanceException extends Exception {}
}
