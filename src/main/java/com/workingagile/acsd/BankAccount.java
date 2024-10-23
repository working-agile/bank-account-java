package com.workingagile.acsd;

public class BankAccount {

    private int balance;
    private int transferFee;

    private FakeEmailSender fakeEmailSender;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
        transferFee = 0;
    }

    public BankAccount(int initialBalance, int transferFee, FakeEmailSender fakeEmailSender) {
        this.balance = initialBalance;
        this.transferFee = transferFee;
        this.fakeEmailSender = fakeEmailSender;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance = balance + amount;
    }

    public void withdraw(int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            fakeEmailSender.sendEmailToBank();
            throw new InsufficientBalanceException();
        }
        balance = balance - amount;
    }

    public void transfer(int amount, BankAccount receiverAccount) throws InsufficientBalanceException {
        withdraw(amount + transferFee);
        receiverAccount.deposit(amount);
    }

    public static class InsufficientBalanceException extends Exception {}
}
