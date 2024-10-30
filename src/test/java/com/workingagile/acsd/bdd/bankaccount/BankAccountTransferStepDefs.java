package com.workingagile.acsd.bdd.bankaccount;

import com.workingagile.acsd.BankAccount;
import com.workingagile.acsd.EmailSender;
import com.workingagile.acsd.TransactionHistory;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class BankAccountTransferStepDefs {

    EmailSender emailSender = mock(EmailSender.class);
    TransactionHistory transactionHistory = mock(TransactionHistory.class);

    BankAccount bankAccountNathan;
    BankAccount bankAccountSabrina;
    int transferFee;

    @Before()
    public void beforeScenario() {
        emailSender = mock(EmailSender.class);
        transactionHistory = mock(TransactionHistory.class);
        transferFee = 0;
    }

    @Given("Nathan with a bank account with balance of {int}")
    public void nathanWithABankAccountWithBalanceOf(int initialBalance) {
        bankAccountNathan = new BankAccount(initialBalance, transferFee, emailSender, transactionHistory);
    }

    @And("Sabrina with a bank account with balance of {int}")
    public void sabrinaWithABankAccountWithBalanceOf(int initialBalance) {
        bankAccountSabrina = new BankAccount(initialBalance, transferFee, emailSender, transactionHistory);
    }

    @When("Nathan transfers {int} from his account to Sabrina's account")
    public void nathanTransfersFromHisAccountToSabrinaSAccount(int amountTransfer) throws Exception {
        bankAccountNathan.transfer(amountTransfer, bankAccountSabrina);
    }

    @Then("Sabrina should now have {int}")
    public void sabrinaShouldNowHave(int expectedBalance) {
        assertThat(bankAccountSabrina.getBalance(), is(equalTo(expectedBalance)));
    }

    @And("Nathan should have left {int} in his account")
    public void nathanShouldHaveLeftInHisAccount(int expectedBalance) {
        assertThat(bankAccountNathan.getBalance(), is(equalTo(expectedBalance)));
    }
}
