package com.workingagile.acsd;

import com.icegreen.greenmail.util.DummySSLSocketFactory;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.Security;

public class EmailIntegrationTest {

    private static GreenMail greenMail;
    private static final int port=3025;
    private static final String bindAddress = "0.0.0.0";
    private static final String protocol = "smtps";
    private static final String user = "test@gmail.com";
    private static final String password = "XXX";

    @BeforeAll
    static void setup() {
        Security.setProperty("ssl.SocketFactory.provider", DummySSLSocketFactory.class.getName());
        greenMail = new GreenMail(new ServerSetup(port, bindAddress, protocol));
        greenMail.setUser(user, password);
        greenMail.start();
    }

    @AfterAll
    static void tearDown() {
        greenMail.stop();
    }



    @DisplayName("Overdrafts trigger email")
    @Test
    void sendEmailWhenOverdraft() {

        // Arrange (Given)


        BankAccount bankAccount = new BankAccount(1000);

        // Act (When)
        bankAccount.deposit(2000);

        // Assert (Then)
        // Verify the email has been sent


    }



}
