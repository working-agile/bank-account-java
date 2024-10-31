package com.workingagile.acsd.bdd.bankaccount;

import com.workingagile.acsd.BankAccount;
import com.workingagile.acsd.EmailSender;
import com.workingagile.acsd.TransactionHistory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class BankAccountEmailStepDefs {

    EmailSender emailSenderMock;
    TransactionHistory transactionHistoryDummy;

    @When("a client overdrafts his account")
    public void a_client_overdrafts_his_account() {

        // Details pushed into the automation layer
        emailSenderMock = mock(EmailSender.class);
        transactionHistoryDummy = mock(TransactionHistory.class);
        BankAccount bankAccount = new BankAccount(100, 5, emailSenderMock, transactionHistoryDummy);

        try {
            bankAccount.withdraw(101);

            fail("Not expected to let withdrawal go through");
        } catch (BankAccount.InsufficientBalanceException e) {
            // expected
        }
    }

    @Then("an email alert is sent to the bank")
    public void anEmailAlertIsSentToTheBank() {

        verify(emailSenderMock, times(1)).sendEmailToBank();

    }
}
