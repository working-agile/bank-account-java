package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountTransferStepDefs {

    BankAccount bankAccountNathan;
    BankAccount bankAccountSabrina;
    int initialBalanceNathan;
    int initialBalanceSabrina;

    @Given("Nathan has a bank account with {int}")
    public void nathan_has_a_bank_account_with(Integer initialBalance) {

        bankAccountNathan = new BankAccount(initialBalance);
        initialBalanceNathan = initialBalance;

    }

    @Given("Sabrina has a bank account with {int}")
    public void sabrina_has_a_bank_account_with(Integer initialBalance) {

        bankAccountSabrina = new BankAccount(initialBalance);
        initialBalanceSabrina = initialBalance;

    }

    Exception exceptionWhenTransferring;

    @When("Nathan tries to transfers {int} to Sabrina")
    @When("Nathan transfers {int} to Sabrina")
    public void nathan_transfers_to_sabrina(Integer transferAmount) {

        try {
            bankAccountNathan.transferWithFee(transferAmount, bankAccountSabrina, transferFee);
        } catch (Exception e) {
            exceptionWhenTransferring = e;
        }

    }

    @Then("the transfer should be cancelled")
    public void the_transfer_should_be_cancelled() {

        assertThat(exceptionWhenTransferring, is(not(nullValue())));
        assertThat(exceptionWhenTransferring, is(instanceOf(BankAccount.InsufficientBalanceException.class)));

    }

    @Then("Sabrina should have {int}")
    public void sabrina_should_have(Integer expectedBalance) {

        assertThat(bankAccountSabrina.getBalance(), is(equalTo(expectedBalance)));

    }

    @Then("Nathan should have {int}")
    public void nathan_should_have(Integer expectedBalance) {

        assertThat(bankAccountNathan.getBalance(), is(equalTo(expectedBalance)));

    }

    @Then("both should have the same amount in their accounts")
    public void both_should_have_the_same_amount_in_their_accounts() {

        assertThat(bankAccountNathan.getBalance(), is(equalTo(initialBalanceNathan)));
        assertThat(bankAccountSabrina.getBalance(), is(equalTo(initialBalanceSabrina)));

    }


    int transferFee;

    @Given("the bank is charging a transfer fee of {int}")
    public void the_bank_is_charging_a_transfer_fee_of(Integer transferFee) {

        this.transferFee = transferFee;

    }




}
