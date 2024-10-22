package com.workingagile.acsd;

public class BankAccount {

    private int balance;
    private int transferenceFee;
    private String customerEmail;
    private EmailSender emailSender;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public BankAccount(int initialBalance, String customerEmail, EmailSender emailSender) {
        this.balance = initialBalance;
        this.customerEmail = customerEmail;
        this.emailSender = emailSender;
    }

    public BankAccount(int initialBalance, int transferenceFee, String customerEmail, EmailSender emailSender) {
        this.balance = initialBalance;
        this.customerEmail = customerEmail;
        this.emailSender = emailSender;
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
            emailSender.send(customerEmail, "Operation not allowed because of insufficient balance!");
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
