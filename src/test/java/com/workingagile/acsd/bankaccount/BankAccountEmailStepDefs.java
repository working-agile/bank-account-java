package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.fail;


public class BankAccountEmailStepDefs {

    @When("a client overdrafts his account")
    public void a_client_overdrafts_his_account() {

        try {

            BankAccount bankAccount = new BankAccount(1000);
            bankAccount.withdraw(2000);
            fail("Not expected to get here");

        } catch (Exception e) {
        }

    }

    @Then("an email alert is sent to the bank")
    public void an_email_alert_is_sent_to_the_bank() {

        // TODO: verify that the email has been sent
        fail("Email has not been sent");
    }

}
