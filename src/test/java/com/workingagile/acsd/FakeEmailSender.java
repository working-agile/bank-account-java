package com.workingagile.acsd;

public class FakeEmailSender implements EmailSender {

    boolean hasSentEmail = false;
    int howManyTimesSentEmail = 0;


    public void sendEmailToBank() {
        System.out.println("Faking that I am sending the email to the bank administration");
        hasSentEmail = true;
        howManyTimesSentEmail++;
    }


}