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

    public void withdraw(Integer amountToWithdraw) throws InsufficientBalanceException {
        if (amountToWithdraw > balance) {
            throw new InsufficientBalanceException();
        }

        balance = balance - amountToWithdraw;
    }

    public void transfer(Integer transferAmount, BankAccount bankAccountReceiver) throws InsufficientBalanceException {
        transferWithFee(transferAmount, bankAccountReceiver, 0);
    }

    public void transferWithFee(Integer transferAmount, BankAccount bankAccountReceiver, Integer transferFee) throws InsufficientBalanceException {
        withdraw(transferAmount + transferFee);
        bankAccountReceiver.deposit(transferAmount);
    }

    public static class InsufficientBalanceException extends Exception {}

}
