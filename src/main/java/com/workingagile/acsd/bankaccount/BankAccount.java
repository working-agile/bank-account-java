package com.workingagile.acsd.bankaccount;

public class BankAccount {

    private int balance;

    public BankAccount(Integer initialBalance) {
        balance = initialBalance;
    }

    public void deposit(Integer amount) {
        balance += amount;
    }

    public Integer getBalance() {
        return balance;
    }
}
