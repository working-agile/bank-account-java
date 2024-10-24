package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TransactionHistoryIntegrationTest {

    @DisplayName("Return the bank statement with in memory transaction history")
    @Test
    public void get_bank_statement_with_in_memory_transaction_history() throws Exception {

        // Given
        EmailSender emailSenderMock = mock(EmailSender.class);
        TransactionHistory transactionHistory = new TransactionHistoryInMemory();
        BankAccount bankAccount = new BankAccount(1000, 0, emailSenderMock, transactionHistory);
        bankAccount.deposit(50);
        bankAccount.withdraw(10);
        bankAccount.deposit(100);
        bankAccount.withdraw(1);
        bankAccount.withdraw(2);

        // When
        String bankStatement = bankAccount.getBankStatement();

        // Then
        assertThat(bankStatement,
                is(equalTo("balance:1137,transactionHistory:[deposit:50,withdrawal:10,deposit:100,withdrawal:1,withdrawal:2]")));
    }

}
