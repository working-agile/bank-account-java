package com.workingagile.acsd.bdd.bankaccount.picocontainer;

import com.workingagile.acsd.BankAccount;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class BankAccountDependencyInjectionDefs2 {

    ScenarioContext context;

    public BankAccountDependencyInjectionDefs2(ScenarioContext context) {
        this.context = context;
    }

    @Before(order = 2)
    public void beforeScenario() {
        System.out.println("2 - Before scenario....");
    }

    @When("Nathan tries to transfers {int} to Sabrina")
    @When("Nathan transfers {int} to Sabrina")
    public void nathanTransfersToSabrina(int amountTransfer) {
        try {
            context.bankAccountNathan.transfer(amountTransfer, context.bankAccountSabrina);
        } catch(Exception e) {
            context.exception = e;
        }
    }

    @Then("Sabrina's account should have {int}")
    @Then("Sabrina should have {int}")
    public void sabrinaShouldHave(int expectedBalance) {
        assertThat(context.bankAccountSabrina.getBalance(), is(equalTo(expectedBalance)));
    }

    @And("Nathan's account should have {int}")
    @And("Nathan should have {int}")
    public void nathanShouldHave(int expectedBalance) {
        assertThat(context.bankAccountNathan.getBalance(), is(equalTo(expectedBalance)));
    }


    @Then("the transfer should be cancelled")
    public void theTransferShouldBeCancelled() {
        assertThat(context.exception, is(not(nullValue())));
        assertThat(context.exception, is(instanceOf(BankAccount.InsufficientBalanceException.class)));
    }

    @And("both should have the same amount in their accounts")
    public void bothShouldHaveTheSameAmountInTheirAccounts() {
        assertThat(context.bankAccountNathan.getBalance(), is(context.initialBalanceNathan));
        assertThat(context.bankAccountSabrina.getBalance(), is(context.initialBalanceSabrina));
    }

    @Then("the transfer is {string}")
    public void theTransferIsStatus(String expectedStatus) {
        if (expectedStatus.equals("successful")) {
            assertThat(context.exception, is(nullValue()));
        } else if (expectedStatus.equals("cancelled")) {
            assertThat(context.exception, is(not(nullValue())));
            assertThat(context.exception, is(instanceOf(BankAccount.InsufficientBalanceException.class)));
        }
    }

}
