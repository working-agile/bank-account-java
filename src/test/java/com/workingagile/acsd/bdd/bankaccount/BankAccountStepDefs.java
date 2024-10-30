package com.workingagile.acsd.bdd.bankaccount;

import com.workingagile.acsd.BankAccount;
import com.workingagile.acsd.EmailSender;
import com.workingagile.acsd.TransactionHistory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.platform.commons.util.Preconditions.notNull;
import static org.mockito.Mockito.mock;

public class BankAccountStepDefs {

    BankAccount myBankAccount;

    @Given("a bank account with the initial balance of {int}")
    public void a_bank_account_with_the_initial_balance_of(int initialBalance)  {

        EmailSender emailSender = mock(EmailSender.class);
        TransactionHistory transactionHistory = mock(TransactionHistory.class);

        myBankAccount = new BankAccount(initialBalance, 0, emailSender,
                transactionHistory);

    }

    @When("a client deposits {int}")
    public void a_client_deposits(int amountToDeposit) {

        myBankAccount.deposit(amountToDeposit);

    }

    @Then("the account should have a balance of {int}")
    public void the_account_should_have_a_balance_of(int expectedBalance) {

        int currentBalance = myBankAccount.getBalance();
        assertThat(currentBalance, is(equalTo(expectedBalance)));
    }

    Exception exception;

    @When("a client withdraws {int}")
    public void aClientWithdraws(int amount) throws BankAccount.InsufficientBalanceException {
        try {
            myBankAccount.withdraw(amount);
        } catch(Exception e) {
            exception = e;
        }
    }

    @Then("the transaction should be cancelled")
    public void theTransactionShouldBeCancelled() {

        assertThat(exception, is(not(nullValue())));
        assertThat(exception, is(instanceOf(BankAccount.InsufficientBalanceException.class)));

    }



}
