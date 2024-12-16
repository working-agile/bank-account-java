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

    Calculator calculator;

    @Given("two addends {int} and {int}")
    public void two_addends_and(Integer addend1, Integer addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
    }

    @When("they are added")
    public void they_are_added() {

        calculator = new Calculator();
        calculator.add(addend1, addend2);

    }

    @Then("the sum should be {int}")
    public void the_sum_should_be(Integer expectedSum) {

        Integer actualSum = calculator.getCurrentValue();

        assertThat(actualSum, is(equalTo(expectedSum)));

    }

}
