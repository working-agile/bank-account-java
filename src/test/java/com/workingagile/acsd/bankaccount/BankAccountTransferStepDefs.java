package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class BankAccountTransferStepDefs {

    BankAccount bankAccountNathan;
    BankAccount bankAccountSabrina;
    int transferFee;

    @Given("Nathan has a bank account with {int}")
    public void nathan_has_a_bank_account_with(Integer initialBalance) {

        bankAccountNathan = new BankAccount(initialBalance);

    }

    @Given("Sabrina has a bank account with {int}")
    public void sabrina_has_a_bank_account_with(Integer initialBalance) {

        bankAccountSabrina = new BankAccount(initialBalance);

    }

    Exception exceptionWhenTransferring;

    @When("Nathan tries to transfers {int} to Sabrina")
    @When("Nathan transfers {int} to Sabrina")
    public void nathan_transfers_to_sabrina(Integer transferAmount) throws BankAccount.InsufficientBalanceException {

        try {
            bankAccountNathan.transfer(transferAmount, bankAccountSabrina);
        } catch (Exception e) {
            exceptionWhenTransferring = e;
        }

    }



    @Then("Sabrina should have {int}")
    public void sabrina_should_have(Integer expectedBalance) {

        assertThat(bankAccountSabrina.getBalance(), is(equalTo(expectedBalance)));

    }

    @Then("Nathan should have {int}")
    public void nathan_should_have(Integer expectedBalance) {

        assertThat(bankAccountNathan.getBalance(), is(equalTo(expectedBalance)));

    }

    @Given("the bank is charging a transfer fee of {int}")
    public void the_bank_is_charging_a_transfer_fee_of(Integer transferFee) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the transfer should be cancelled")
    public void the_transfer_should_be_cancelled() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("both should have the same amount in their accounts")
    public void both_should_have_the_same_amount_in_their_accounts() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
