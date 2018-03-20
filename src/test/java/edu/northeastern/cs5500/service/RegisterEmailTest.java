package edu.northeastern.cs5500.service;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Praveen Singh
 */
public class RegisterEmailTest {

    private RegisterEmail registerEmail;

    @Before
    public void setUp(){
        registerEmail = new RegisterEmail();
    }

    @Test(expected = RuntimeException.class)
    public void sendEmailShouldThrowException(){
        registerEmail.sendEmail("praveen", null);
    }

    @Test
    public void sendEmailShouldActuallySendEmail(){
        registerEmail.sendEmail("praveen", "msdteam207@gmail.com");
    }

}