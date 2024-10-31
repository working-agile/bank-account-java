package com.workingagile.acsd.bdd.bankaccount.bankstatement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;

public class TransactionHistoryStepDefs {

    @Given("Nathan with a bank account and initial balance of {int}")
    public void nathanWithABankAccountAndInitialBalanceOf(int initialBalance) {

        // TODO
        // 1. Create a bank account object with the initial balance

    }


    @And("the following transaction history")
    public void theFollowingTransactionHistory(DataTable transactions) {

        // TODO
        // 2. Execute the transactions against the bank account

    }


    @When("Nathan requests a bank statement")
    public void nathanRequestsABankStatement() {

        // TODO
        // 3. Request the bank account statement

    }

    @Then("he should receive a bank statement containing:")
    public void heShouldReceiveABankStatementContaining(String bankStatement) {

        // TODO
        // 4. Compare the expected bank account statement with the result from 3.

    }

}
