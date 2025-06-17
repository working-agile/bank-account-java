package com.workingagile.acsd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TestContainerTests {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    private static String url;
    private static String username;
    private static String password;


    @BeforeAll
    static void beforeAll() {
        postgres.start();
        url = postgres.getJdbcUrl();
        username = postgres.getUsername();
        password = postgres.getPassword();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        System.out.println("JdbcUrl: " + url  );
        System.out.println("Username: " +  username);
        System.out.println("Password: " +  password);

        createCustomersTableIfNotExists();

    }


    private void createCustomersTableIfNotExists() {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "create table if not exists bank_transactions ( " +
                    "   id bigint NOT NULL, " +
                    "   amount integer " +
                    ") "
            );
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void tests_with_real_test_database() {

        try {
            TransactionHistory transactionHistory = new TransactionHistoryPostgres(url, username, password);
            EmailSender emailSenderDummy = mock(EmailSender.class);

            BankAccount bankAccount = new BankAccount(1000, 0, emailSenderDummy, transactionHistory);

            bankAccount.deposit(50);
            bankAccount.withdraw(10);
            bankAccount.deposit(100);
            bankAccount.withdraw(1);
            bankAccount.withdraw(2);
            String bankStatement = bankAccount.getBankStatement();

            // Verify result
            assertThat(bankStatement,
                    is(equalTo("balance:1137,transactionHistory:[deposit:50,withdrawal:10,deposit:100,withdrawal:1,withdrawal:2]")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}