package com.workingagile.acsd.addition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdditionStepDefs {

    int addend1;
    int addend2;

    @Given("two addends {int} and {int}")
    public void two_addends_and(Integer addend1, Integer addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
    }

    int sum;

    @When("they are added")
    public void they_are_added() {

        sum = addend1 + addend2;
    }

    @Then("the sum should be {int}")
    public void the_sum_should_be(Integer expectedSum) {
        assertThat(sum, is(equalTo(expectedSum)));
    }

}
