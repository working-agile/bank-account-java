package com.workingagile.acsd.bdd.bankaccount.picocontainer;

import com.workingagile.acsd.BankAccount;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


public class BankAccountDependencyInjectionDefs1 {

    ScenarioContext context;

    public BankAccountDependencyInjectionDefs1(ScenarioContext context) {
        this.context = context;
    }

    @Before(order = 1)
    public void beforeScenario() {
        System.out.println("1 - Before scenario....");
    }

    @Given("Nathan has a bank account with {int}")
    public void nathanHasABankAccountWith(int initialBalance) {
        context.bankAccountNathan = new BankAccount(initialBalance,
                context.transferFee, context.emailSender, context.transactionHistory);
        context.initialBalanceNathan = initialBalance;
    }

    @And("Sabrina has a bank account with {int}")
    public void sabrinaHasABankAccountWith(int initialBalance) {
        context.bankAccountSabrina = new BankAccount(initialBalance,
                context.transferFee, context.emailSender, context.transactionHistory);
        context.initialBalanceSabrina = initialBalance;
    }

    @And("the bank is charging a transfer fee of {int}")
    public void theBankIsChargingATransferFeeOf(int transferFee) {
        context.transferFee = transferFee;
    }
}
