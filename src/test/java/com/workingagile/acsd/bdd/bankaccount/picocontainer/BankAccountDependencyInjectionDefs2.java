package com.workingagile.acsd.bdd.bankaccount.picocontainer;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountDependencyInjectionDefs2 {

    ScenarioContext context;

    public BankAccountDependencyInjectionDefs2(ScenarioContext context) {
        this.context = context;
    }

    @Before(order = 2)
    public void beforeScenario() {
        System.out.println("2 - Before scenario....");
    }

    @When("Nathan transfers {int} to Sabrina")
    public void nathanTransfersToSabrina(int amountTransfer) throws Exception {
        context.bankAccountNathan.transfer(amountTransfer, context.bankAccountSabrina);
    }

    @Then("Sabrina should have {int}")
    public void sabrinaShouldHave(int expectedBalance) {
        assertThat(context.bankAccountSabrina.getBalance(), is(equalTo(expectedBalance)));
    }

    @And("Nathan should have {int}")
    public void nathanShouldHave(int expectedBalance) {
        assertThat(context.bankAccountNathan.getBalance(), is(equalTo(expectedBalance)));
    }
}
