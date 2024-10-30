package com.workingagile.acsd.bdd.bankaccount.picocontainer;

import com.workingagile.acsd.BankAccount;
import com.workingagile.acsd.EmailSender;
import com.workingagile.acsd.TransactionHistory;
import org.picocontainer.Disposable;
import org.picocontainer.Startable;

import static org.mockito.Mockito.mock;

public class ScenarioContext implements Disposable, Startable {

    public Exception exception;
    public int initialBalanceNathan;
    public int initialBalanceSabrina;
    EmailSender emailSender;
    TransactionHistory transactionHistory;

    BankAccount bankAccountNathan;
    BankAccount bankAccountSabrina;
    int transferFee;

    @Override
    public void start() {
        System.out.println("Pico-Container: start");
        emailSender = mock(EmailSender.class);
        transactionHistory = mock(TransactionHistory.class);
        transferFee = 0;
    }

    @Override
    public void stop() {
        System.out.println("Pico-Container: stop");
    }

    @Override
    public void dispose() {
        System.out.println("Pico-Container: dispose");
    }


}
